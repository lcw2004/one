package com.lcw.one.workflow.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 2017-05-11.
 * @auth Licw
 */
@Entity
@Table(name = "flow_audit_log")
public class FlowAuditLogEO {
    private String auditLogId;
    private String auditItemId;
    private Date auditTime;
    private String userId;
    private Integer result;
    private String remark;
    private String operateName;

    @Id
    @Column(name = "audit_log_id")
    public String getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(String auditLogId) {
        this.auditLogId = auditLogId;
    }

    @Basic
    @Column(name = "audit_item_id")
    public String getAuditItemId() {
        return auditItemId;
    }

    public void setAuditItemId(String auditItemId) {
        this.auditItemId = auditItemId;
    }

    @Basic
    @Column(name = "audit_time")
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "result")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "operate_name")
    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

}
