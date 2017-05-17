package com.lcw.one.user.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "dict_user_operate")
public class DictUserOperateEO {
    private String operateCode;
    private String operateName;

    @Id
    @Column(name = "operate_code")
    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    @Basic
    @Column(name = "operate_name")
    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictUserOperateEO that = (DictUserOperateEO) o;

        if (operateCode != null ? !operateCode.equals(that.operateCode) : that.operateCode != null) return false;
        if (operateName != null ? !operateName.equals(that.operateName) : that.operateName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operateCode != null ? operateCode.hashCode() : 0;
        result = 31 * result + (operateName != null ? operateName.hashCode() : 0);
        return result;
    }
}
