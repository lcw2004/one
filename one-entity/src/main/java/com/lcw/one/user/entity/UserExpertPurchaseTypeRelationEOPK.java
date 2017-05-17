package com.lcw.one.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
public class UserExpertPurchaseTypeRelationEOPK implements Serializable {
    private String userId;
    private String purchaseTypeId;

    @Column(name = "user_id")
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "purchase_type_id")
    @Id
    public String getPurchaseTypeId() {
        return purchaseTypeId;
    }

    public void setPurchaseTypeId(String purchaseTypeId) {
        this.purchaseTypeId = purchaseTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserExpertPurchaseTypeRelationEOPK that = (UserExpertPurchaseTypeRelationEOPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (purchaseTypeId != null ? !purchaseTypeId.equals(that.purchaseTypeId) : that.purchaseTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (purchaseTypeId != null ? purchaseTypeId.hashCode() : 0);
        return result;
    }
}
