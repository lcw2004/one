package com.lcw.one.codegen.bean;

import java.util.List;

public class EntityMetaInfo extends CodeMetaInfo {

    private String tableName;
    private List<FieldMetaInfo> fieldMetaInfoList;

    // ID的class
    private Class idFieldClass;

    // 是否是关联表
    private boolean isRelationTable;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<FieldMetaInfo> getFieldMetaInfoList() {
        return fieldMetaInfoList;
    }

    public void setFieldMetaInfoList(List<FieldMetaInfo> fieldMetaInfoList) {
        this.fieldMetaInfoList = fieldMetaInfoList;
    }

    public Class getIdFieldClass() {
        return idFieldClass;
    }

    public void setIdFieldClass(Class idFieldClass) {
        this.idFieldClass = idFieldClass;
    }

    public boolean isRelationTable() {
        return isRelationTable;
    }

    public void setRelationTable(boolean relationTable) {
        isRelationTable = relationTable;
    }
}
