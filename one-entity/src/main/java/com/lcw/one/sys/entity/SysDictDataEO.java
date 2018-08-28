package com.lcw.one.sys.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_dict_data")
public class SysDictDataEO {

    @Id
    @Column(name = "data_id")
    private String dataId;

    @Basic
    @Column(name = "dict_id")
    private String dictId;

    @Basic
    @Column(name = "label")
    private String label;

    @Basic
    @Column(name = "value")
    private String value;

    @Basic
    @Column(name = "sort")
    private Integer sort;

    @Basic
    @Column(name = "remark")
    private String remark;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
