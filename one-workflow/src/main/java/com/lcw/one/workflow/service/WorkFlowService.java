package com.lcw.one.workflow.service;

import com.lcw.one.notify.service.IMessageSenderService;
import com.lcw.one.user.constant.AuditStatusEnum;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.NumberUtils;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.UUID;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.bean.constant.FlowEffectiveEnum;
import com.lcw.one.workflow.bean.constant.FlowFinishEnum;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import com.lcw.one.workflow.service.flow.ActivitiService;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
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

    public WorkFlowBean startWorkflow(String flowId, String businessId, String secondBusinessId, String businessName, String operateName, LoginUser loginUser, Map<String, Object> variables) {
        return this.startWorkflow(flowId, businessId, secondBusinessId, businessName, operateName, loginUser, variables, null);
    }

    /**
     * 启动工作流
     */
    public WorkFlowBean startWorkflow(String flowId, String businessId, String secondBusinessId, String businessName, String operateName, LoginUser loginUser, Map<String, Object> variables, String assigneeId) {
        String flowInstanceId = null;
        WorkFlowBean workFlowBeanReturn = null;
        try {
            String auditItemId = UUID.randomUUID();

            // 启动工作流，并获取流程实例ID
            WorkFlowBean workFlowBean = new WorkFlowBean();
            workFlowBean.setUserId(loginUser.getUserId());
            workFlowBean.setAssigneeId(assigneeId);
            workFlowBean.setFlowId(flowId);
            workFlowBean.setBusinessKey(businessId);
            workFlowBean.putVariables("businessId", businessId);
            workFlowBean.putVariables("businessName", businessName);
            workFlowBean.putVariables("auditItemId", auditItemId);
            workFlowBean.putVariables("applyUserId", loginUser.getUserId());
            workFlowBean.putVariables("applyUserName", loginUser.getUserName());
            workFlowBean.putVariables("applyOfficeId", loginUser.getOfficeId());
            if (CollectionUtils.isNotEmpty(variables)) {
                // 如果有需要覆盖的参数，由variables设置同名称参数进行覆盖
                workFlowBean.putVariables(variables);
            }
            workFlowBeanReturn = activitiService.startWorkflow(workFlowBean);
            flowInstanceId = workFlowBeanReturn.getProcessInstanceId();

            // 保存审核事项
            FlowAuditItemEO flowAuditItemEO = new FlowAuditItemEO();
            flowAuditItemEO.setAuditItemId(auditItemId);
            flowAuditItemEO.setBusinessType(flowId);
            flowAuditItemEO.setBusinessId(businessId);
            flowAuditItemEO.setSecondBusinessId(secondBusinessId);
            flowAuditItemEO.setTaskName(workFlowBeanReturn.getTaskDefinitionName());
            flowAuditItemEO.setApplyUserId(loginUser.getUserId());
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
            flowAuditLogEO.setUserId(loginUser.getUserId());
            flowAuditLogEO.setResult(null);
            flowAuditLogEO.setRemark("");
            flowAuditLogEO.setOperateName(operateName);
            flowAuditLogEO.setIp(loginUser.getIp());
            flowAuditLogEOService.save(flowAuditLogEO);
        } catch (Exception e) {
            if (StringUtils.isNotEmpty(flowInstanceId)) {
                activitiService.deleteWorkflowInstance(flowInstanceId, "flowId[" + flowId + "]执行异常");
            }
            handlerException(e);
        }
        return workFlowBeanReturn;
    }

    public void audit(LoginUser loginUser, String taskId, Boolean auditResult, String remark, Map<String, Object> variables) {
        this.audit(loginUser, taskId, auditResult, remark, variables, null);
    }

    /**
     * 审核工单
     *
     * @param loginUser   当前用户
     * @param taskId      任务ID
     * @param auditResult 审批结果
     * @param remark      审批不通过的理由
     */
    @Transactional(rollbackOn = Exception.class)
    public void audit(LoginUser loginUser, String taskId, Boolean auditResult, String remark, Map<String, Object> variables, Map<String, Object> localVariables) {
        if (!auditResult && StringUtils.isEmpty(remark)) {
            throw new OneBaseException("请输入驳回原因");
        }

        try {
            TaskInfoBean taskInfoBean = activitiService.getTask(taskId);

            String auditItemId = (String) taskInfoBean.getVariables().get("auditItemId");
            FlowAuditItemEO flowAuditItemEO = flowAuditItemEOService.get(auditItemId);

            // 记录审核日志
            FlowAuditLogEO flowAuditLogEO = new FlowAuditLogEO();
            flowAuditLogEO.setAuditLogId(UUID.randomUUID());
            flowAuditLogEO.setAuditItemId(auditItemId);
            flowAuditLogEO.setAuditTime(new Date());
            flowAuditLogEO.setUserId(loginUser.getUserId());
            flowAuditLogEO.setIp(loginUser.getIp());
            flowAuditLogEO.setResult(NumberUtils.boolToInt(auditResult));
            flowAuditLogEO.setRemark(remark);
            flowAuditLogEO.setOperateName(taskInfoBean.getTaskName());
            flowAuditLogEOService.save(flowAuditLogEO);

            // 执行工作流
            WorkFlowBean workFlowBean = new WorkFlowBean();
            workFlowBean.setUserId(loginUser.getUserId());
            workFlowBean.setBusinessKey(flowAuditItemEO.getBusinessId());
            workFlowBean.setFlowId(flowAuditItemEO.getFlowDefinitionId());
            workFlowBean.setTaskId(taskInfoBean.getTaskId());
            workFlowBean.putVariables("auditUserId", loginUser.getUserId());
            workFlowBean.putVariables("auditResult", auditResult);
            workFlowBean.putVariables("auditRemark", remark);
            workFlowBean.putVariables("loginUser", loginUser);
            workFlowBean.putVariables(variables);
            workFlowBean.putLocalVariables(localVariables);
            activitiService.execWorkflow(workFlowBean);
        } catch (Exception e) {
            handlerException(e);
        }
    }

    /**
     * 处理工作流异常
     *
     * @param e
     */
    private void handlerException(Exception e) {
        if (e instanceof ActivitiException) {
            Throwable cause = e.getCause();
            if (cause != null) {
                if (cause instanceof OneBaseException) {
                    throw (OneBaseException) cause;
                } else {
                    logger.error(cause.getMessage(), cause);
                    throw new OneBaseException(cause.getMessage());
                }
            }
        } else {
            throw new OneBaseException(e.getMessage());
        }
    }

    public TaskInfoBean getTask(String taskId) {
        return activitiService.getTask(taskId);
    }

    public String getBusinessId(String taskId) {
        TaskInfoBean taskInfoBean = this.getTask(taskId);
        return  (String) taskInfoBean.getVariables().get("businessId");
    }

}
