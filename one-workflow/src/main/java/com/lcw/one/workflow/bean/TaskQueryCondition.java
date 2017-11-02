package com.lcw.one.workflow.bean;

import com.lcw.one.util.http.bean.BaseQueryCondition;

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
}
