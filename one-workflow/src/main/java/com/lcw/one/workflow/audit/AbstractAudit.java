package com.lcw.one.workflow.audit;

import com.lcw.one.base.utils.OneLinkUtils;
import com.lcw.one.notify.service.IMessageSenderService;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
import com.lcw.one.workflow.service.FlowAuditItemEOService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAudit implements JavaDelegate, IDoAudit {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAudit.class);

    @Autowired
    protected IMessageSenderService iMessageSenderService;

    @Autowired
    protected FlowAuditItemEOService flowAuditItemEOService;

    @Override
    public void execute(DelegateExecution execution) {
        logger.info("ExecutionId: {}, ProcessInstanceId: {}", execution.getId(), execution.getProcessInstanceId());
        Boolean auditResult = (Boolean) execution.getVariable("auditResult");
        String auditRemark = (String) execution.getVariable("auditRemark");
        String auditItemId = (String) execution.getVariable("auditItemId");

        FlowAuditItemEO flowAuditItemEO = flowAuditItemEOService.get(auditItemId);

        // 调用审核处理接口
        if (!auditResult) {
            doReject(execution);
            logger.info("Do reject on item: " + flowAuditItemEO.getAuditItemId());
            notifyUser(execution, false, auditRemark);
            flowAuditItemEOService.updateStateWhenReject(auditItemId, auditRemark);
        } else {
            doAgree(execution);
            logger.info("Do agree on item: " + flowAuditItemEO.getAuditItemId());
            notifyUser(execution, true, null);
            flowAuditItemEOService.updateStateWhenPass(auditItemId);
        }
    }

    @Override
    public abstract void doAgree(DelegateExecution execution);

    @Override
    public abstract void doReject(DelegateExecution execution);

    /**
     * 将审核结果通知用户，（如果每个工作流不一样，请覆盖该方法）
     */
    public void notifyUser(DelegateExecution execution, Boolean isAuditPass, String auditRemark) {
        String processDefinitionId = execution.getProcessDefinitionId();
        processDefinitionId = processDefinitionId.substring(0, processDefinitionId.indexOf(":"));
        String messageTemplateId = isAuditPass ? "10011" : "10012";
        String businessId = (String) execution.getVariable("businessId");
        String businessName = (String) execution.getVariable("businessName");
        String applyUserName = (String) execution.getVariable("applyUserName");
        String applyUserId = (String) execution.getVariable("applyUserId");
        String auditType = (String) execution.getVariable("processName_");
        String businessLink = OneLinkUtils.getBusinessLink(processDefinitionId, businessId, businessName);

        Map<String, Object> placeholderMap = new HashMap<>();
        placeholderMap.put("businessName", businessLink);
        placeholderMap.put("applyUserName", applyUserName);
        placeholderMap.put("applyUserId", applyUserId);
        placeholderMap.put("auditType", auditType);
        placeholderMap.put("auditRemark", auditRemark);

        iMessageSenderService.sendMessage(applyUserId, messageTemplateId, placeholderMap);
    }

    protected String getBusinessId(DelegateExecution execution) {
        return (String) execution.getVariable("businessId");
    }

    protected String getAuditRemark(DelegateExecution execution) {
        return (String) execution.getVariable("auditRemark");
    }



}
