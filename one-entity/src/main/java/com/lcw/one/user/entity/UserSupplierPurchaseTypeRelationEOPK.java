package com.lcw.one.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
public class UserSupplierPurchaseTypeRelationEOPK implements Serializable {
    private String supplierId;
    private String purchaseTypeId;

    @Column(name = "supplier_id")
    @Id
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

        UserSupplierPurchaseTypeRelationEOPK that = (UserSupplierPurchaseTypeRelationEOPK) o;

        if (supplierId != null ? !supplierId.equals(that.supplierId) : that.supplierId != null) return false;
        if (purchaseTypeId != null ? !purchaseTypeId.equals(that.purchaseTypeId) : that.purchaseTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = supplierId != null ? supplierId.hashCode() : 0;
        result = 31 * result + (purchaseTypeId != null ? purchaseTypeId.hashCode() : 0);
        return result;
    }
}
