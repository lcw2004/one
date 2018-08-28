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
    private Integer isShowInMenu = 0;

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

    @Transient
    public Integer getIsShowInMenu() {
        return isShowInMenu;
    }

    public void setIsShowInMenu(Integer isShowInMenu) {
        this.isShowInMenu = isShowInMenu;
    }

}
