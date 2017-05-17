package com.lcw.one.sys.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_org_role")
@IdClass(SysOrgRoleEOPK.class)
public class SysOrgRoleEO {
    private String officeId;
    private String roleId;

    @Id
    @Column(name = "office_id")
    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Id
    @Column(name = "role_id")
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

        SysOrgRoleEO that = (SysOrgRoleEO) o;

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
