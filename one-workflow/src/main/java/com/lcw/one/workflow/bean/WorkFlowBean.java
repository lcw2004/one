package com.lcw.one.workflow.bean;

import com.lcw.one.util.utils.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WorkFlowBean {

    private String userId;

    private String assigneeId;

    private String flowId;

    private String taskId;

    private String businessKey;

    private String taskDefinitionKey;

    private String taskDefinitionName;

    private String processDefinitionId;

    private String processInstanceId;

    private Map<String, Object> variables = new HashMap<>();

    private Map<String, Object> localVariables = new HashMap<>();

    private Map<String, Object> resultMap = new HashMap<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getTaskDefinitionName() {
        return taskDefinitionName;
    }

    public void setTaskDefinitionName(String taskDefinitionName) {
        this.taskDefinitionName = taskDefinitionName;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Map<String, Object> getVariables() {
        if (this.variables == null) {
            this.variables = new HashMap<>();
        }
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public Map<String, Object> getLocalVariables() {
        if (this.localVariables == null) {
            this.localVariables = new HashMap<>();
        }
        return localVariables;
    }

    public void setLocalVariables(Map<String, Object> localVariables) {
        this.localVariables = localVariables;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void putVariables(String key, Object value) {
        getVariables().put(key, value);
    }

    public void putVariables(Map<String, Object> variables) {
        if (CollectionUtils.isNotEmpty(variables)) {
            getVariables().putAll(variables);
        }
    }

    public void putLocalVariables(String key, Object value) {
        getLocalVariables().put(key, value);
    }

    public void putLocalVariables(Map<String, Object> variables) {
        if (CollectionUtils.isNotEmpty(variables)) {
            getLocalVariables().putAll(variables);
        }
    }
}
