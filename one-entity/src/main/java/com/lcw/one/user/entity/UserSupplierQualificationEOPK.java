package com.lcw.one.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 2017-04-30.
 * @auth Licw
 */
public class UserSupplierQualificationEOPK implements Serializable {
    private String supplierId;
    private String qualificationCode;

    @Column(name = "supplier_id")
    @Id
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Column(name = "qualification_code")
    @Id
    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSupplierQualificationEOPK that = (UserSupplierQualificationEOPK) o;

        if (supplierId != null ? !supplierId.equals(that.supplierId) : that.supplierId != null) return false;
        if (qualificationCode != null ? !qualificationCode.equals(that.qualificationCode) : that.qualificationCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = supplierId != null ? supplierId.hashCode() : 0;
        result = 31 * result + (qualificationCode != null ? qualificationCode.hashCode() : 0);
        return result;
    }
}
