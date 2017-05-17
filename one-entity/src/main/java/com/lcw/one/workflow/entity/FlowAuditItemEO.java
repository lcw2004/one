package com.lcw.one.workflow.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Date;

/**
 * @version 2017-05-11.
 * @auth Licw
 */
@Entity
@Table(name = "flow_audit_item")
public class FlowAuditItemEO {
    private String auditItemId;
    private String taskName;
    private String businessType;
    private String applyUserId;
    private Date startTime;
    private Date effectTime;
    private Integer auditStatus;
    private String flowInstanceId;
    private String flowDefinitionId;
    private String businessId;
    private Integer isEffective;
    private Integer isFinished;
    private String remark;

    @Id
    @Column(name = "audit_item_id")
    public String getAuditItemId() {
        return auditItemId;
    }

    public void setAuditItemId(String auditItemId) {
        this.auditItemId = auditItemId;
    }

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "apply_user_id")
    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    @Basic
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "effect_time")
    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    @Basic
    @Column(name = "audit_status")
    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Basic
    @Column(name = "flow_instance_id")
    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public void setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
    }

    @Basic
    @Column(name = "flow_definition_id")
    public String getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(String flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }

    @Basic
    @Column(name = "business_id")
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Basic
    @Column(name = "is_effective")
    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    @Basic
    @Column(name = "is_finished")
    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
