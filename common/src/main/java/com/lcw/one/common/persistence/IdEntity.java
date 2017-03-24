/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.common.persistence;

import com.lcw.one.common.util.IdGen;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;

/**
 * 数据Entity类
 * @author ThinkGem
 * @version 2013-05-28
 */
@MappedSuperclass
public abstract class IdEntity<T> extends BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;        // 编号

    public IdEntity() {
        super();
    }

    @PrePersist
    public void prePersist() {
        this.id = IdGen.uuid();
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
