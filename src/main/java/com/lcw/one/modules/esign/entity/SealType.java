package com.lcw.one.modules.esign.entity;

import javax.persistence.*;

/**
 * Created by lcw on 16-6-17.
 */
@Entity
@Table(name = "E_SEAL_TYPE")
public class SealType {
    private int id;
    private String name;
    private String code;
    private Integer type;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SealType sealType = (SealType) o;

        if (id != sealType.id) return false;
        if (name != null ? !name.equals(sealType.name) : sealType.name != null) return false;
        if (code != null ? !code.equals(sealType.code) : sealType.code != null) return false;
        if (type != null ? !type.equals(sealType.type) : sealType.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
