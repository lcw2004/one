package com.lcw.one.sys.entity;

import javax.persistence.*;

/**
 * @version 2017-04-16.
 * @auth Licw
 */
@Entity
@Table(name = "sys_dict")
public class SysDictEO {
    private String id;
    private String label;
    private String value;
    private String type;
    private String description;
    private Integer sort;
    private String remarks;
    private Integer delFlag;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "del_flag")
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysDictEO sysDictEO = (SysDictEO) o;

        if (id != null ? !id.equals(sysDictEO.id) : sysDictEO.id != null) return false;
        if (label != null ? !label.equals(sysDictEO.label) : sysDictEO.label != null) return false;
        if (value != null ? !value.equals(sysDictEO.value) : sysDictEO.value != null) return false;
        if (type != null ? !type.equals(sysDictEO.type) : sysDictEO.type != null) return false;
        if (description != null ? !description.equals(sysDictEO.description) : sysDictEO.description != null)
            return false;
        if (sort != null ? !sort.equals(sysDictEO.sort) : sysDictEO.sort != null) return false;
        if (remarks != null ? !remarks.equals(sysDictEO.remarks) : sysDictEO.remarks != null) return false;
        if (delFlag != null ? !delFlag.equals(sysDictEO.delFlag) : sysDictEO.delFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        return result;
    }
}
