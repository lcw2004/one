/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.lcw.one.sys.utils.DictUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "sys_menu")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu extends TreeEntity<Menu> {

    private static final long serialVersionUID = 1L;

    private String name;    // 名称
    private String href;    // 链接
    private String target;    // 目标（ mainFrame、_blank、_self、_parent、_top）
    private String icon;    // 图标
    private Integer sort;
    private String isShow;    // 是否在菜单中显示（1：显示；0：不显示）
    private String isShowCN;    // 是否在菜单中显示（1：显示；0：不显示）
    private String isActiviti;    // 是否同步到工作流（1：同步；0：不同步）
    private String permission; // 权限标识
    @JsonIgnore
    private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

    public Menu() {
        super();
        this.sort = 30;
    }

    public Menu(String id) {
        this();
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @NotNull
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Length(min = 1, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 255)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Length(min = 0, max = 20)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Length(min = 0, max = 100)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Length(min = 1, max = 1)
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Length(min = 1, max = 1)
    public String getIsActiviti() {
        return isActiviti;
    }

    public void setIsActiviti(String isActiviti) {
        this.isActiviti = isActiviti;
    }

    @Length(min = 0, max = 200)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @ManyToMany(mappedBy = "menuList", fetch = FetchType.LAZY)
    @Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
    @OrderBy("id")
    @Fetch(FetchMode.SUBSELECT)
    @NotFound(action = NotFoundAction.IGNORE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Transient
    public boolean isRoot() {
        return isRoot(this.id);
    }

    @Transient
    public static boolean isRoot(String id) {
        return id != null && id.equals("1");
    }

    @Transient
    public String getActivitiGroupId() {
        return ObjectUtils.toString(getPermission());
    }

    @Transient
    public String getActivitiGroupName() {
        return ObjectUtils.toString(getId());
    }

    @Transient
    public String getIsShowCN() {
        return DictUtils.getDictLabel(isShow, "show_hide", null);
    }

    public void setIsShowCN(String isShowCN) {
        this.isShowCN = isShowCN;
    }
}