package com.lcw.one.codegen.entity;

import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;

@Entity
@Table(name = "code_gen_table_field")
@IdClass(CodeGenTableFieldEOPK.class)
public class CodeGenTableFieldEO {

    @Id
    @Column(name = "table_id")
    private String tableId;

    @Id
    @Column(name = "field_id")
    private String fieldId;

    @Basic
    @Column(name = "field_type")
    private String fieldType;

    @Basic
    @Column(name = "field_name")
    private String fieldName;

    @Basic
    @Column(name = "java_type")
    private String javaType;

    @Basic
    @Column(name = "java_field_name")
    private String javaFieldName;

    @Basic
    @Column(name = "is_edit")
    private Integer isEdit;

    @Basic
    @Column(name = "is_insert")
    private Integer isInsert;

    @Basic
    @Column(name = "is_list")
    private Integer isList;

    @Basic
    @Column(name = "is_query")
    private Integer isQuery;

    @Basic
    @Column(name = "show_type")
    private String showType;

    @Basic
    @Column(name = "dict_type")
    private String dictType;

    @Basic
    @Column(name = "query_type")
    private String queryType;

    @Basic
    @Column(name = "is_primary_key")
    private Integer isPrimaryKey;

    @Basic
    @Column(name = "is_autoincrement")
    private Integer isAutoincrement;

    @Basic
    @Column(name = "is_required")
    private Integer isRequired;

    @Basic
    @Column(name = "java_field_include")
    private String javaFieldInclude;

    @Basic
    @Column(name = "order_index")
    private Integer orderIndex;

    public String getTableId() {
        return this.tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getJavaType() {
        return this.javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaFieldName() {
        return this.javaFieldName;
    }

    public void setJavaFieldName(String javaFieldName) {
        this.javaFieldName = javaFieldName;
    }

    public Integer getIsEdit() {
        return this.isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }

    public Integer getIsInsert() {
        return this.isInsert;
    }

    public void setIsInsert(Integer isInsert) {
        this.isInsert = isInsert;
    }

    public Integer getIsList() {
        return this.isList;
    }

    public void setIsList(Integer isList) {
        this.isList = isList;
    }

    public Integer getIsQuery() {
        return this.isQuery;
    }

    public void setIsQuery(Integer isQuery) {
        this.isQuery = isQuery;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDictType() {
        return this.dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getQueryType() {
        return this.queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Integer getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(Integer isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public Integer getIsAutoincrement() {
        return isAutoincrement;
    }

    public void setIsAutoincrement(Integer isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public String getJavaFieldInclude() {
        return javaFieldInclude;
    }

    public void setJavaFieldInclude(String javaFieldInclude) {
        this.javaFieldInclude = javaFieldInclude;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Transient
    public boolean isPrimaryKey() {
        return this.isPrimaryKey == 1;
    }

    @Transient
    public boolean isAutoincrement() {
        return this.isAutoincrement == 1;
    }

    @Transient
    public boolean isRequired() {
        return this.isRequired == 1;
    }

}




