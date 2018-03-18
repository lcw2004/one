package com.lcw.one.baseInfo.entity;

import com.lcw.one.util.persistence.entity.TreeEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @version 2017-10-08.
 * @auth Licw
 */
@Entity
@Table(name = "base_archive_menu")
public class BaseArchiveMenuEO extends TreeEntity<BaseArchiveMenuEO> {
    private String remark;
    private String view;

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "view")
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
