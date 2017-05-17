package com.lcw.one.baseInfo.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "base_purchase_type_audit_user")
@IdClass(BasePurchaseTypeAuditUserRelationEOPK.class)
public class BasePurchaseTypeAuditUserRelationEO {
    private String userId;
    private String purchaseTypeId;

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
}
