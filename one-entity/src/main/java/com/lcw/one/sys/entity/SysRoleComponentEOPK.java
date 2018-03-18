package com.lcw.one.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;

public class SysRoleComponentEOPK implements Serializable {

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Id
    @Column(name = "component_id")
    private String componentId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getComponentId () {
        return this.componentId;
    }

    public void setComponentId (String componentId) {
        this.componentId = componentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRoleComponentEOPK that = (SysRoleComponentEOPK) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (componentId != null ? !componentId.equals(that.componentId) : that.componentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (componentId != null ? componentId.hashCode() : 0);
        return result;
    }

}