package com.lcw.one.user.entity;

import com.lcw.one.baseInfo.entity.BasePurchaseTypeEO;
import com.lcw.one.user.constant.AuditStatusEnum;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "user_expert_purchase_type_relation")
@IdClass(UserExpertPurchaseTypeRelationEOPK.class)
public class UserExpertPurchaseTypeRelationEO {
    private String userId;
    private String purchaseTypeId;
    private Integer status;
    private String statusCN;
    private String remark;
    private BasePurchaseTypeEO basePurchaseType;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @ManyToOne
    @JoinColumn(name = "purchase_type_id", insertable = false, updatable = false)
    public BasePurchaseTypeEO getBasePurchaseType() {
        return basePurchaseType;
    }

    public void setBasePurchaseType(BasePurchaseTypeEO basePurchaseType) {
        this.basePurchaseType = basePurchaseType;
    }

}
