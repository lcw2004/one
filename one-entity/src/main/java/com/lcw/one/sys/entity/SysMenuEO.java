package com.lcw.one.sys.entity;

import com.lcw.one.util.persistence.entity.TreeEntity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_menu")
public class SysMenuEO extends TreeEntity<SysMenuEO>{

    private String href;
    private String icon;
    private Integer sort;
    private Integer isShow;
    private String permission;
    private String remarks;

    @Basic
    @Column(name = "href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "is_show")
    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "permission")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysMenuEO sysMenuEO = (SysMenuEO) o;

        if (id != null ? !id.equals(sysMenuEO.id) : sysMenuEO.id != null) return false;
        if (parentId != null ? !parentId.equals(sysMenuEO.parentId) : sysMenuEO.parentId != null) return false;
        if (parentIds != null ? !parentIds.equals(sysMenuEO.parentIds) : sysMenuEO.parentIds != null) return false;
        if (name != null ? !name.equals(sysMenuEO.name) : sysMenuEO.name != null) return false;
        if (href != null ? !href.equals(sysMenuEO.href) : sysMenuEO.href != null) return false;
        if (icon != null ? !icon.equals(sysMenuEO.icon) : sysMenuEO.icon != null) return false;
        if (sort != null ? !sort.equals(sysMenuEO.sort) : sysMenuEO.sort != null) return false;
        if (isShow != null ? !isShow.equals(sysMenuEO.isShow) : sysMenuEO.isShow != null) return false;
        if (permission != null ? !permission.equals(sysMenuEO.permission) : sysMenuEO.permission != null) return false;
        if (delFlag != null ? !delFlag.equals(sysMenuEO.delFlag) : sysMenuEO.delFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (parentIds != null ? parentIds.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (isShow != null ? isShow.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        return result;
    }
}
