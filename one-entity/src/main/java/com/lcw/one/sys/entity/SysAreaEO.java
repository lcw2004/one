package com.lcw.one.sys.entity;

import com.lcw.one.util.persistence.entity.TreeEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_area")
public class SysAreaEO extends TreeEntity<SysAreaEO> {

    @Basic
    @Column(name = "code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
