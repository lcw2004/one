package com.lcw.one.workflow.entity;

import com.google.gson.Gson;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.util.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 2017-09-14.
 * @auth Licw
 */
public class FlowAuditItemBean {

    private String auditItemId;
    private String businessId;
    private LoginUser loginUser;
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

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
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
        if (StringUtils.isNotEmpty((String) map.get("loginUser"))) {
            this.loginUser = GsonUtil.fromJson((String) map.get("loginUser"), LoginUser.class);
        }
        this.auditResult = (Boolean) map.get("auditResult");
        this.auditRemark = (String) map.get("auditRemark");
        this.variables = (Map<String, Object>) map.get("variables");
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("auditItemId", this.auditItemId);
        map.put("businessId", this.businessId);
        map.put("loginUser", GsonUtil.toJson(this.loginUser));
        map.put("auditResult", this.auditResult);
        map.put("auditRemark", this.auditRemark);
        map.put("variables", this.variables);
        return map;
    }


}
