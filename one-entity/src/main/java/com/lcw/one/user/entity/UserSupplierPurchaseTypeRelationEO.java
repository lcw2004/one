package com.lcw.one.user.entity;

import com.lcw.one.baseInfo.entity.BasePurchaseTypeEO;
import com.lcw.one.user.constant.AuditStatusEnum;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_supplier_purchase_type_relation")
@IdClass(UserSupplierPurchaseTypeRelationEOPK.class)
public class UserSupplierPurchaseTypeRelationEO {
    private String supplierId;
    private String purchaseTypeId;
    private Integer status;
    private String statusCN;
    private String remark;
    private String auditUserId;
    private Boolean isCanAudit;
    private BasePurchaseTypeEO basePurchaseType;
    private UserInfoEO userInfoEO;

    @Id
    @Column(name = "supplier_id")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Id
    @Column(name = "purchase_type_id")
    public String getPurchaseTypeId() {
        return purchaseTypeId;
    }

    public void setPurchaseTypeId(String purchaseTypeId) {
        this.purchaseTypeId = purchaseTypeId;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public String getStatusCN() {
        statusCN = AuditStatusEnum.get(status).getLabel();
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
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
    @Column(name = "audit_user_id")
    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    @ManyToOne
    @JoinColumn(name = "purchase_type_id", insertable = false, updatable = false)
    public BasePurchaseTypeEO getBasePurchaseType() {
        return basePurchaseType;
    }

    public void setBasePurchaseType(BasePurchaseTypeEO basePurchaseType) {
        this.basePurchaseType = basePurchaseType;
    }

    @ManyToOne
    @JoinColumn(name = "audit_user_id", insertable = false, updatable = false)
    public UserInfoEO getUserInfoEO() {
        return userInfoEO;
    }

    public void setUserInfoEO(UserInfoEO userInfoEO) {
        this.userInfoEO = userInfoEO;
    }

    @Transient
    public Boolean getCanAudit() {
        return isCanAudit;
    }

    public void setCanAudit(Boolean canAudit) {
        isCanAudit = canAudit;
    }
}
