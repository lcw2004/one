package com.lcw.one.workflow.service;

import com.lcw.one.user.constant.AuditStatusEnum;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.UUID;
import com.lcw.one.workflow.audit.IDoAudit;
import com.lcw.one.workflow.audit.IDoAuditFactory;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.bean.constant.FlowEffectiveEnum;
import com.lcw.one.workflow.bean.constant.FlowFinishEnum;
import com.lcw.one.workflow.config.ThreadLocalContext;
import com.lcw.one.workflow.entity.FlowAuditItemBean;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import com.lcw.one.workflow.service.flow.ActivitiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

/**
 * 工作流接口调用相关服务
 */
@Service
public class WorkFlowService {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowService.class);

    @Autowired
    private FlowAuditItemEOService flowAuditItemEOService;

    @Autowired
    private FlowAuditLogEOService flowAuditLogEOService;

    @Autowired
    private ActivitiService activitiService;

    /**
     * 启动工作流
     *
     * @param flowId      流程ID（同时作为业务ID，工作流名称作为业务名称）
     * @param businessId  业务ID
     * @param applyUserId 申请人ID
     */
    public void startWorkflow(String flowId, String businessId, String secondBusinessId, String businessName, String applyUserId, String applyUserName, String operateName, String ip, Map<String, Object> variables) {
        String auditItemId = UUID.randomUUID();
        String flowInstanceId = null;
        try {
            // 启动工作流，并获取流程实例ID
            WorkFlowBean workFlowBean = new WorkFlowBean();
            workFlowBean.setUserId(applyUserId);
            workFlowBean.setFlowId(flowId);
            workFlowBean.setBusinessKey(businessId);
            workFlowBean.putVariables("businessId", businessId);
            workFlowBean.putVariables("businessName", businessName);
            workFlowBean.putVariables("auditItemId", auditItemId);
            workFlowBean.putVariables("applyUserId", applyUserId);
            workFlowBean.putVariables("applyUserName", applyUserName);
            if (CollectionUtils.isNotEmpty(variables)) {
                workFlowBean.putVariables(variables);
            }
            WorkFlowBean workFlowBeanReturn = activitiService.startWorkflow(workFlowBean);
            flowInstanceId = workFlowBeanReturn.getProcessInstanceId();

            // 保存审核事项
            FlowAuditItemEO flowAuditItemEO = new FlowAuditItemEO();
            flowAuditItemEO.setAuditItemId(auditItemId);
            flowAuditItemEO.setBusinessType(flowId);
            flowAuditItemEO.setBusinessId(businessId);
            flowAuditItemEO.setSecondBusinessId(secondBusinessId);
            flowAuditItemEO.setTaskName(workFlowBeanReturn.getTaskDefinitionName());
            flowAuditItemEO.setApplyUserId(applyUserId);
            flowAuditItemEO.setStartTime(new Date());
            flowAuditItemEO.setAuditStatus(AuditStatusEnum.AUDIT.getValue());
            flowAuditItemEO.setFlowDefinitionId(flowId);
            flowAuditItemEO.setFlowInstanceId(flowInstanceId);
            flowAuditItemEO.setIsEffective(FlowEffectiveEnum.NOT_EFFECTIVE.getValue());
            flowAuditItemEO.setIsFinished(FlowFinishEnum.NOT_FINISH.getValue());
            flowAuditItemEOService.save(flowAuditItemEO);

            // 记录工作流日志
            FlowAuditLogEO flowAuditLogEO = new FlowAuditLogEO();
            flowAuditLogEO.setAuditLogId(UUID.randomUUID());
            flowAuditLogEO.setAuditItemId(auditItemId);
            flowAuditLogEO.setAuditTime(new Date());
            flowAuditLogEO.setUserId(applyUserId);
            flowAuditLogEO.setResult(null);
            flowAuditLogEO.setRemark("");
            flowAuditLogEO.setOperateName(operateName);
            flowAuditLogEO.setIp(ip);
            flowAuditLogEOService.save(flowAuditLogEO);
        } catch (Exception e) {
            if (StringUtils.isNotEmpty(flowInstanceId)) {
                activitiService.deleteWorkflowInstance(flowInstanceId, "flowId[" + flowId + "]执行异常");
            }

            Map<String, String> map = ThreadLocalContext.threadLocal.get();
            if (map != null && !map.isEmpty()) {
                throw new RuntimeException(map.get("cause"));
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     * 审核工单
     *
     * @param taskId      任务ID
     * @param auditUserId 审批人ID
     * @param auditResult 审批结果
     * @param remark      审批不通过的理由
     */
    @Transactional(rollbackOn = Exception.class)
    public void audit(LoginUser loginUser, String taskId, Boolean auditResult, String remark) {
        TaskInfoBean taskInfoBean = activitiService.getTask(taskId);

        String auditItemId = (String) taskInfoBean.getVariables().get("auditItemId");
        FlowAuditItemEO flowAuditItemEO = flowAuditItemEOService.get(auditItemId);
        String taskName = taskInfoBean.getTaskName();
        String flowId = flowAuditItemEO.getFlowDefinitionId();
        String taskDefinitionKey = taskInfoBean.getTaskDefinitionKey();
        Boolean isFinalAudit = "finalAudit".equals(taskDefinitionKey);

        // 记录审核日志
        FlowAuditLogEO flowAuditLogEO = new FlowAuditLogEO();
        flowAuditLogEO.setAuditLogId(UUID.randomUUID());
        flowAuditLogEO.setAuditItemId(auditItemId);
        flowAuditLogEO.setAuditTime(new Date());
        flowAuditLogEO.setUserId(loginUser.getUserId());
        flowAuditLogEO.setIp(loginUser.getIp());
        flowAuditLogEO.setResult(auditResult ? 1 : 0);
        flowAuditLogEO.setRemark(remark);
        flowAuditLogEO.setOperateName(auditResult ? "审核通过" : "审核驳回");
        flowAuditLogEOService.save(flowAuditLogEO);

        FlowAuditItemBean flowAuditItemBean = new FlowAuditItemBean();
        flowAuditItemBean.setAuditItemId(auditItemId);
        flowAuditItemBean.setBusinessId(flowAuditItemEO.getBusinessId());
        flowAuditItemBean.setLoginUser(loginUser);
        flowAuditItemBean.setAuditRemark(remark);
        flowAuditItemBean.setAuditResult(auditResult);
        flowAuditItemBean.setVariables(taskInfoBean.getVariables());

        // 调用审核处理接口
        IDoAudit iDoAudit = IDoAuditFactory.buildIDoAudit(flowId);
        if (!auditResult) {
            // 驳回
            // 任何一个人驳回都结束工单
            logger.info("Do reject on item: " + flowAuditItemEO.getAuditItemId());
            iDoAudit.doReject(flowAuditItemBean);
            flowAuditItemEOService.updateStateWhenReject(auditItemId, remark);
        } else {
            // 通过
            if (isFinalAudit) {
                // 最后一个通过
                logger.info("Do agree on item: " + flowAuditItemEO.getAuditItemId());
                iDoAudit.doAgree(flowAuditItemBean);
                flowAuditItemEOService.updateStateWhenPass(auditItemId);
            }
        }

        // 执行工作流
        WorkFlowBean workFlowBean = new WorkFlowBean();
        workFlowBean.setUserId(loginUser.getUserId());
        workFlowBean.setBusinessKey(flowAuditItemEO.getBusinessId());
        workFlowBean.setFlowId(flowAuditItemEO.getFlowDefinitionId());
        workFlowBean.setTaskId(taskId);
        workFlowBean.putVariables("auditResult", auditResult);
        activitiService.execWorkflow(workFlowBean);
    }
}
