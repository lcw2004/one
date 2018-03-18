package com.lcw.one.codegen.bean;

import com.lcw.one.codegen.util.NameUtils;

public class TableFieldMetaInfo {

    private String classField;

    private String classFieldUpperCamel;

    private String tableField;

    private String comment;

    private String tableFieldType;

    private String javaType;

    private int length;

    private int precision;

    private boolean isPrimaryKey;

    private boolean isAutoincrement;

    private boolean isRequired;

    private String include;

    public String getClassField() {
        return classField;
    }

    public void setClassField(String classField) {
        this.classField = classField;
    }

    public String getClassFieldUpperCamel() {
        return NameUtils.upperFirstChar(classField);
    }

    public String getTableField() {
        return tableField;
    }

    public void setTableField(String tableField) {
        this.tableField = tableField;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTableFieldType() {
        return tableFieldType;
    }

    public void setTableFieldType(String tableFieldType) {
        this.tableFieldType = tableFieldType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean primary) {
        this.isPrimaryKey = primary;
    }

    public boolean getIsAutoincrement() {
        return isAutoincrement;
    }

    public void setIsAutoincrement(boolean isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }

    public boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isNullable) {
        this.isRequired = isNullable;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }
}
