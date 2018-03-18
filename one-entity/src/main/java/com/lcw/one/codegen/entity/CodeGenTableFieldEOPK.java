package com.lcw.one.codegen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;

public class CodeGenTableFieldEOPK implements Serializable {

    @Id
    @Column(name = "table_id")
    private String tableId;

    @Id
    @Column(name = "field_id")
    private String fieldId;


    public String getTableId () {
        return this.tableId;
    }

    public void setTableId (String tableId) {
        this.tableId = tableId;
    }

    public String getFieldId () {
        return this.fieldId;
    }

    public void setFieldId (String fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeGenTableFieldEOPK that = (CodeGenTableFieldEOPK) o;

        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
        if (fieldId != null ? !fieldId.equals(that.fieldId) : that.fieldId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (tableId != null ? tableId.hashCode() : 0);
        result = 31 * result + (fieldId != null ? fieldId.hashCode() : 0);
        return result;
    }

}




