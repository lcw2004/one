package com.lcw.one.workflow.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 2017-04-08.
 * @auth Licw
 */
public class WorkFlowBean {

    private String userId;
    private String flowId;
    private String businessKey;
    private Map<String, Object> paramMap = new HashMap<>();
    private Map<String, Object> resultMap = new HashMap<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
