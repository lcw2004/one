package com.lcw.one.util.excel.rule;

import com.lcw.one.util.annotation.ExcelField;

import java.lang.reflect.Field;

public class ExcelFieldRule {

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 标题
     */
    private String title;

    /**
     * 在Excel中的列号
     */
    private int index;

    /**
     *
     */
    private int align;

    /**
     * 是否可以为空：true - 可以，false - 不可以
     */
    private boolean nullable;

    /**
     * 字段class，无需传入，用反射获取
     */
    private Class fieldClass;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 时间格式化
     */
    private String format;

    public ExcelFieldRule () {
    }

    public ExcelFieldRule(Field field, ExcelField excelField) {
        this.fieldName = field.getName();
        this.fieldClass = field.getType();
        this.title = excelField.title();
        this.index = excelField.column();
        this.align = excelField.align();
        this.nullable = excelField.nullable();
        this.dictType = excelField.dict();
    }

    public ExcelFieldRule(String fieldName, String title, int index, int align, boolean nullable, Class fieldClass) {
        this.fieldName = fieldName;
        this.title = title;
        this.index = index;
        this.align = align;
        this.nullable = nullable;
        this.fieldClass = fieldClass;
    }

    public ExcelFieldRule(String fieldName, String title, int index, int align, boolean nullable, Class fieldClass, String dictType) {
        this(fieldName, title, index, align, nullable, fieldClass);
        this.dictType = dictType;
    }

    public ExcelFieldRule(String fieldName, String title, int index, int align, boolean nullable, Class fieldClass, String dictType, String format) {
        this(fieldName, title, index, align, nullable, fieldClass);
        this.dictType = dictType;
        this.format = format;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Class getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(Class fieldClass) {
        this.fieldClass = fieldClass;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "ExcelFieldRule{" +
                "fieldName='" + fieldName + '\'' +
                ", title='" + title + '\'' +
                ", index=" + index +
                ", align=" + align +
                ", fieldClass=" + fieldClass +
                '}';
    }
}
