package com.lcw.one.util.excel.rule;

import com.lcw.one.util.annotation.ExcelField;
import com.lcw.one.util.annotation.ExcelIndex;
import com.lcw.one.util.utils.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 2017-05-19.
 * @auth Licw
 */
public class ExcelClassRule {

    /**
     * 用来排序的字段名
     */
    private String orderField;

    /**
     * Excel解析规则
     */
    private List<ExcelFieldRule> fieldRuleList;

    private Integer index = 0;

    public ExcelClassRule() {
    }
    public ExcelClassRule(Class clazz) {
        from(clazz);
    }

    public void addExcelFieldRule(ExcelFieldRule excelFieldRule) {
        if (this.getFieldRuleList() == null) {
            this.fieldRuleList = new ArrayList<>();
        }
        if (excelFieldRule.getIndex() == null) {
            excelFieldRule.setIndex(this.index++);
        }
        this.fieldRuleList.add(excelFieldRule);
    }

    /**
     * 从指定的类里面获取每一个字段的类型
     *
     * @param clazz
     */
    public void fetchFieldClass(Class clazz) {
        for (ExcelFieldRule excelFieldRule : this.getFieldRuleList()) {
            Class fieldClazz = Reflections.getFiledType(clazz, excelFieldRule.getFieldName());
            excelFieldRule.setFieldClass(fieldClazz);
        }
    }

    /**
     * 将一个class转换Excel解析规则
     * @param clazz
     */
    public void from(Class clazz) {
        this.fieldRuleList = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 解析排序字段
            if (field.isAnnotationPresent(ExcelIndex.class)) {
                this.setOrderField(field.getName());
            }

            // 解析Excel行号及标题
            if (!field.isAnnotationPresent(ExcelField.class)) {
                continue;
            }

            ExcelField excelField = field.getAnnotation(ExcelField.class);
            ExcelFieldRule excelFieldRule = new ExcelFieldRule(field, excelField);

            this.fieldRuleList.add(excelFieldRule);
        }
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public List<ExcelFieldRule> getFieldRuleList() {
        return fieldRuleList;
    }

    public void setFieldRuleList(List<ExcelFieldRule> fieldRuleList) {
        this.fieldRuleList = fieldRuleList;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
