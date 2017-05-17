package com.lcw.one.baseInfo.entity;

import com.lcw.one.util.persistence.entity.TreeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "base_profession_type")
public class BaseProfessionTypeEO extends TreeEntity<BaseProfessionTypeEO> {

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
