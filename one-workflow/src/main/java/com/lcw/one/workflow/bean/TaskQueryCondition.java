package com.lcw.one.workflow.bean;

import com.lcw.one.util.http.bean.BaseQueryCondition;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class TaskQueryCondition extends BaseQueryCondition {

    // 角色ID
    private String roleIds;

    // 用户ID
    private String userId;

    // 流程实例ID
    private String processInstanceId;

    // 业务参数ID（variables.processBusinessKey）
    private String businessKey;

    // 流程节点ID
    private String taskDefinitionKey;

    // 流程ID列表
    private String processDefinitionKeys;

    // 业务数据ID
    private String businessId;

    // 业务数据名称
    private String businessName;

    // 工作流状态
    private String workflowStatus;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public TaskQuery createTaskQuery(TaskQuery taskQuery) {
        // 角色ID
        if (StringUtils.isNotEmpty(this.getRoleIds())) {
            taskQuery = taskQuery.taskCandidateGroupIn(Arrays.asList(this.getRoleIds().split(",")));
        }
        // 用户ID
        if (StringUtils.isNotEmpty(this.getUserId())) {
            taskQuery = taskQuery.taskAssignee(this.getUserId());
        }
        // 流程实例ID
        if (StringUtils.isNotEmpty(this.getProcessInstanceId())) {
            taskQuery = taskQuery.processInstanceId(this.getProcessInstanceId());
        }
        // 业务参数ID
        if (StringUtils.isNotEmpty(this.getBusinessKey())) {
            taskQuery = taskQuery.processInstanceBusinessKey(this.getBusinessKey());
        }
        // 流程节点ID
        if (StringUtils.isNotEmpty(this.getTaskDefinitionKey())) {
            taskQuery = taskQuery.taskDefinitionKey(this.getTaskDefinitionKey());
        }
        // 流程ID
        if (StringUtils.isNotEmpty(this.getProcessDefinitionKeys())) {
            List<String> processDefinitionKeyList = Arrays.asList(this.getProcessDefinitionKeys().split(","));
            taskQuery = taskQuery.processDefinitionKeyIn(processDefinitionKeyList);
        }
        // 业务数据ID
        if (StringUtils.isNotEmpty(this.getBusinessId())) {
            taskQuery = taskQuery.processVariableValueLike("businessId", "%" + this.getBusinessId() + "%");
        }
        // 业务数据名称
        if (StringUtils.isNotEmpty(this.getBusinessName())) {
            taskQuery = taskQuery.processVariableValueLike("businessName", "%" + this.getBusinessName() + "%");
        }
        return taskQuery;
    }

    public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery(HistoricTaskInstanceQuery taskQuery) {
        // 用户ID
        if (StringUtils.isNotEmpty(this.getUserId())) {
            taskQuery = taskQuery.taskAssignee(this.getUserId());
        }
        // 流程实例ID
        if (StringUtils.isNotEmpty(this.getProcessInstanceId())) {
            taskQuery = taskQuery.processInstanceId(this.getProcessInstanceId());
        }
        // 业务参数ID
        if (StringUtils.isNotEmpty(this.getBusinessKey())) {
            taskQuery = taskQuery.processInstanceBusinessKey(this.getBusinessKey());
        }
        // 流程节点ID
        if (StringUtils.isNotEmpty(this.getTaskDefinitionKey())) {
            taskQuery = taskQuery.taskDefinitionKey(this.getTaskDefinitionKey());
        }
        // 流程ID
        if (StringUtils.isNotEmpty(this.getProcessDefinitionKeys())) {
            List<String> processDefinitionKeyList = Arrays.asList(this.getProcessDefinitionKeys().split(","));
            taskQuery = taskQuery.processDefinitionKeyIn(processDefinitionKeyList);
        }
        // 业务数据ID
        if (StringUtils.isNotEmpty(this.getBusinessId())) {
            taskQuery = taskQuery.processVariableValueLike("businessId", "%" + this.getBusinessId() + "%");
        }
        // 业务数据名称
        if (StringUtils.isNotEmpty(this.getBusinessName())) {
            taskQuery = taskQuery.processVariableValueLike("businessName", "%" + this.getBusinessName() + "%");
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
