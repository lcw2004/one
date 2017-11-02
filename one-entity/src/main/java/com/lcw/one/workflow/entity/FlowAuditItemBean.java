package com.lcw.one.workflow.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 2017-09-14.
 * @auth Licw
 */
public class FlowAuditItemBean {

    private String auditItemId;
    private String businessId;
    private String userId;
    private String ip;
    private boolean auditResult;
    private String auditRemark;
    private Map<String, Object> variables;

    public String getAuditItemId() {
        return auditItemId;
    }

    public void setAuditItemId(String auditItemId) {
        this.auditItemId = auditItemId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(boolean auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public FlowAuditItemBean() {
    }

    public FlowAuditItemBean(Map<String, Object> map) {
        this.auditItemId = (String) map.get("auditItemId");
        this.businessId = (String) map.get("businessId");
        this.userId = (String) map.get("userId");
        this.ip = (String) map.get("ip");
        this.auditResult = (Boolean) map.get("auditResult");
        this.auditRemark = (String) map.get("auditRemark");
        this.variables = (Map<String, Object>) map.get("variables");
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("auditItemId", this.auditItemId);
        map.put("businessId", this.businessId);
        map.put("userId", this.userId);
        map.put("ip", this.ip);
        map.put("auditResult", this.auditResult);
        map.put("auditRemark", this.auditRemark);
        map.put("variables", this.variables);
        return map;
    }


}
