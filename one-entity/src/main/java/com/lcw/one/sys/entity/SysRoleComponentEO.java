package com.lcw.one.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;

/**
 * 角色组件关联
 */
@Entity
@Table(name = "sys_role_component")
@IdClass(SysRoleComponentEOPK.class)
public class SysRoleComponentEO {

	/**
	 * 角色ID
	 */
	@Id
	@Column(name = "role_id")
	private String roleId;

	/**
	 * 组件编号
	 */
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

}