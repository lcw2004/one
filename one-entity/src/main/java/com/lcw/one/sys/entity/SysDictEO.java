package com.lcw.one.sys.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_dict")
public class SysDictEO {

    @Id
    @Column(name = "dict_id")
    private String dictId;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "remark")
    private String remark;

    @Basic
    @Column(name = "del_flag")
    private Integer delFlag;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
