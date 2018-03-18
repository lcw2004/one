package com.lcw.one.sys.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_role")
public class SysRoleEO {
    private String id;
    private String name;
    private Integer dataScope;
    private String remarks;
    private Integer delFlag;
    private Integer isDefault;
    private List<SysMenuEO> sysMenuEOList = new ArrayList<>();
    private List<String> sysMenuEOIdList = new ArrayList<>();
    private List<String> componentIdList = new ArrayList<>();

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "data_scope")
    public Integer getDataScope() {
        return dataScope;
    }

    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "del_flag")
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Basic
    @Column(name = "is_default")
    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRoleEO sysRoleEO = (SysRoleEO) o;

        if (id != null ? !id.equals(sysRoleEO.id) : sysRoleEO.id != null) return false;
        if (name != null ? !name.equals(sysRoleEO.name) : sysRoleEO.name != null) return false;
        if (dataScope != null ? !dataScope.equals(sysRoleEO.dataScope) : sysRoleEO.dataScope != null) return false;
        if (remarks != null ? !remarks.equals(sysRoleEO.remarks) : sysRoleEO.remarks != null) return false;
        if (delFlag != null ? !delFlag.equals(sysRoleEO.delFlag) : sysRoleEO.delFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dataScope != null ? dataScope.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        return result;
    }

    @Transient
    public List<SysMenuEO> getSysMenuEOList() {
        return sysMenuEOList;
    }

    public void setSysMenuEOList(List<SysMenuEO> sysMenuEOList) {
        this.sysMenuEOList = sysMenuEOList;
    }

    @Transient
    public List<String> getSysMenuEOIdList() {
        return sysMenuEOIdList;
    }

    public void setSysMenuEOIdList(List<String> sysMenuEOIdList) {
        this.sysMenuEOIdList = sysMenuEOIdList;
    }

    @Transient
    public List<String> getComponentIdList() {
        return componentIdList;
    }

    public void setComponentIdList(List<String> componentIdList) {
        this.componentIdList = componentIdList;
    }
}
