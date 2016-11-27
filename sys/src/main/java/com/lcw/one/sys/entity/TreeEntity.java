/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lcw.one.sys.entity;

import com.lcw.one.common.util.Reflections;
import com.lcw.one.common.util.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据Entity类
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class TreeEntity<T extends TreeEntity> extends DataEntity<T> {

    private static final long serialVersionUID = 1L;

    protected T parent;    // 父级编号
    protected String parentIds; // 所有父级编号
    protected String parentId; // 所有父级编号
    protected List<T> childList;

    public abstract T getParent();

    public abstract void setParent(T parent);

    public abstract String getParentIds();

    public abstract void setParentIds(String parentIds);

    @Transient
    public String getParentId() {
        if (getParent() != null) {
            parentId = getParent().getId();
        } else {
            parentId = "0";
        }
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Transient
    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        this.childList = childList;
    }

}
