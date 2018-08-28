package com.lcw.one.workflow.bean;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.bean.BaseQueryCondition;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.workflow.bean.constant.FlowQueryTypeEnum;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.TaskQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskQueryCondition extends BaseQueryCondition {

    /**
     * 角色ID
     */
    private String roleIds;

    /**
     * 用户ID
     */
    private String assigneeId;

    /**
     * 申请人ID
     */
    private String applyUserId;

    /**
     * 审批机构ID
     */
    private String auditOfficeId;

    /**
     * 1. 根据角色查询
     * 2. 根据assigneeId查询
     * 3. 根据发起人查询
     */
    private Integer queryType;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 业务参数ID（variables.processBusinessKey）
     */
    private String businessKey;

    /**
     * 流程节点ID
     */
    private String taskDefinitionKey;

    /**
     * 流程ID列表
     */
    private String processDefinitionKeys;

    /**
     * 业务数据ID
     */
    private String businessId;

    /**
     * 业务数据名称
     */
    private String businessName;

    /**
     * 工作流状态：0 - 未结束， 1 - 已结束
     */
    private String workflowStatus;

    /**
     * 审批结果： 0 - 未通过， 1 - 已通过
     */
    private String auditResult;
    
    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public String getAuditOfficeId() {
        return auditOfficeId;
    }

    public void setAuditOfficeId(String auditOfficeId) {
        this.auditOfficeId = auditOfficeId;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getProcessDefinitionKeys() {
        return processDefinitionKeys;
    }

    public void setProcessDefinitionKeys(String processDefinitionKeys) {
        this.processDefinitionKeys = processDefinitionKeys;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private List<String> listCandidateGroup() {
        String officeId = getCurrentUser().getOfficeId();
        String userId = getCurrentUser().getUserId();
        List<String> roleIdList = StringUtils.stringToList(getCurrentUser().getRoleIds());

        List<String> candidateGroupList = new ArrayList<>();
        candidateGroupList.add(CandidateUtils.office(officeId));
        candidateGroupList.add(CandidateUtils.user(userId));
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            for (String roleId : roleIdList) {
                candidateGroupList.add(CandidateUtils.role(roleId));
                candidateGroupList.add(CandidateUtils.officeAndRole(officeId, roleId));
            }
        }
        return candidateGroupList;
    }

    public TaskQuery createTaskQuery(TaskQuery taskQuery) {
        // ------ 设置用户数据 ------
        if (getQueryType() == FlowQueryTypeEnum.BY_ROLE_AND_ASSIGNEE_ID.getValue()) {
            taskQuery = taskQuery.taskCandidateOrAssigned(getCurrentUser().getUserId());
            taskQuery = taskQuery.taskCandidateGroupIn(listCandidateGroup());
        } else if (getQueryType() == FlowQueryTypeEnum.BY_ASSIGNEE_ID.getValue()) {
            taskQuery = taskQuery.taskCandidateOrAssigned(getCurrentUser().getUserId());
        } else if (getQueryType() == FlowQueryTypeEnum.BY_APPLY_USER_ID.getValue()) {
            taskQuery = taskQuery.processVariableValueEquals("applyUserId", getCurrentUser().getUserId());
        } else {
            throw new OneBaseException("请先设置工作流查询类型");
        }
        
        // ------ 流程信息 ------
        // 流程实例ID
        if (StringUtils.isNotEmpty(getProcessInstanceId())) {
            taskQuery = taskQuery.processInstanceId(getProcessInstanceId());
        }
        // 业务参数ID
        if (StringUtils.isNotEmpty(getBusinessKey())) {
            taskQuery = taskQuery.processInstanceBusinessKey(getBusinessKey());
        }
        // 流程节点ID
        if (StringUtils.isNotEmpty(getTaskDefinitionKey())) {
            taskQuery = taskQuery.taskDefinitionKeyLike(getTaskDefinitionKey() + "%");
        }
        // 流程ID
        if (StringUtils.isNotEmpty(getProcessDefinitionKeys())) {
            List<String> processDefinitionKeyList = StringUtils.stringToList(getProcessDefinitionKeys());
            taskQuery = taskQuery.processDefinitionKeyIn(processDefinitionKeyList);
        }

        // ------ 业务信息 ------
        // 业务数据ID
        if (StringUtils.isNotEmpty(getBusinessId())) {
            taskQuery = taskQuery.processVariableValueLike("businessId", "%" + getBusinessId() + "%");
        }
        // 业务数据名称
        if (StringUtils.isNotEmpty(getBusinessName())) {
            taskQuery = taskQuery.processVariableValueLike("businessName", "%" + getBusinessName() + "%");
        }
        // 开始时间
        if (StringUtils.isNotEmpty(getStartTime())) {
            taskQuery.taskCreatedAfter(DateUtils.stringToDate(getStartTime(), DateUtils.yyyy_MM_dd_EN));
        }
        // 结束时间
        if (StringUtils.isNotEmpty(getEndTime())) {
            taskQuery.taskCreatedBefore(DateUtils.getDay235959(DateUtils.stringToDate(getEndTime(), DateUtils.yyyy_MM_dd_EN)));
        }

        taskQuery = taskQuery.includeTaskLocalVariables().includeProcessVariables();
        return taskQuery;
    }

    public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery(HistoricTaskInstanceQuery taskQuery) {
        // 用户ID
        if (StringUtils.isNotEmpty(getAssigneeId())) {
            taskQuery = taskQuery.taskAssignee(getAssigneeId());
        }
        // 申请人ID
        if (StringUtils.isNotEmpty(getApplyUserId())) {
            taskQuery = taskQuery.processVariableValueEquals("applyUserId", getApplyUserId());
        }
        // 流程实例ID
        if (StringUtils.isNotEmpty(getProcessInstanceId())) {
            taskQuery = taskQuery.processInstanceId(getProcessInstanceId());
        }
        // 业务参数ID
        if (StringUtils.isNotEmpty(getBusinessKey())) {
            taskQuery = taskQuery.processInstanceBusinessKey(getBusinessKey());
        }
        // 流程节点ID
        if (StringUtils.isNotEmpty(getTaskDefinitionKey())) {
            taskQuery = taskQuery.taskDefinitionKey(getTaskDefinitionKey());
        }
        // 流程ID
        if (StringUtils.isNotEmpty(getProcessDefinitionKeys())) {
            List<String> processDefinitionKeyList = Arrays.asList(getProcessDefinitionKeys().split(","));
            taskQuery = taskQuery.processDefinitionKeyIn(processDefinitionKeyList);
        }
        // 业务数据ID
        if (StringUtils.isNotEmpty(getBusinessId())) {
            taskQuery = taskQuery.processVariableValueLike("businessId", "%" + getBusinessId() + "%");
        }
        // 业务数据名称
        if (StringUtils.isNotEmpty(getBusinessName())) {
            taskQuery = taskQuery.processVariableValueLike("businessName", "%" + getBusinessName() + "%");
        }
        // 开始时间
        if (StringUtils.isNotEmpty(getStartTime())) {
            taskQuery.taskCreatedAfter(DateUtils.stringToDate(getStartTime(), DateUtils.yyyy_MM_dd_EN));
        }
        // 结束时间
        if (StringUtils.isNotEmpty(getEndTime())) {
            taskQuery.taskCreatedBefore(DateUtils.getDay235959(DateUtils.stringToDate(getEndTime(), DateUtils.yyyy_MM_dd_EN)));
        }
        // 审批状态
        if (StringUtils.isNotEmpty(getAuditResult())) {
            taskQuery = taskQuery.processVariableValueEquals("auditResult", "1".equals(getAuditResult()));
        }
        if (StringUtils.isNotEmpty(getWorkflowStatus())) {
            if ("0".equals(getWorkflowStatus())) {
                taskQuery.unfinished();
            } else if ("1".equals(getWorkflowStatus())) {
                taskQuery.finished();
            }
        }
        return taskQuery;
    }
}
