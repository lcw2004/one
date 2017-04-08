package com.lcw.one.workflow.bean;

import java.util.Map;

/**
 * @version 2017-04-08.
 * @auth Licw
 */
public class WorkFlowBean {

    private String userId;
    private String flowId;
    private String businessKey;
    private Map<String, String> paramMap;

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

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }
}
