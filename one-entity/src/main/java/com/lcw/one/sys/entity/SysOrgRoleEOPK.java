package com.lcw.one.sys.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
public class SysOrgRoleEOPK implements Serializable {
    private String officeId;
    private String roleId;

    @Column(name = "office_id")
    @Id
    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Column(name = "role_id")
    @Id
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysOrgRoleEOPK that = (SysOrgRoleEOPK) o;

        if (officeId != null ? !officeId.equals(that.officeId) : that.officeId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = officeId != null ? officeId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
