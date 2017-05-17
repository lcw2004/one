package com.lcw.one.baseInfo.entity;

import com.lcw.one.util.persistence.entity.TreeEntity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "base_purchase_type")
public class BasePurchaseTypeEO extends TreeEntity<BasePurchaseTypeEO> {

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
