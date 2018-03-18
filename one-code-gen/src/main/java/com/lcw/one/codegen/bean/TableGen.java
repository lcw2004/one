package com.lcw.one.codegen.bean;

import com.lcw.one.codegen.entity.CodeGenTableEO;
import com.lcw.one.codegen.entity.CodeGenTableFieldEO;
import com.lcw.one.codegen.util.NameUtils;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.ObjectUtils;

import java.util.LinkedList;
import java.util.List;

public class TableGen extends CodeGenTableEO {

    private String className;
    private String packageName;
    private List<TableFieldGen> fieldList;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<TableFieldGen> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<TableFieldGen> fieldList) {
        this.fieldList = fieldList;
    }

    /**
     * 是否是联合主键
     */
    public Boolean getIsJointKey() {
        if (CollectionUtils.isEmpty(fieldList)) {
            return false;
        }

        int primaryKeyCount = 0;
        for (CodeGenTableFieldEO tableField : fieldList) {
            if (tableField.getIsPrimaryKey() == 1) {
                primaryKeyCount++;
            }
        }

        return primaryKeyCount > 1;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNameLowerCamel() {
        return NameUtils.lowerFirstChar(getClassName());
    }

    public String getClassNameNoEO() {
        if (getClassName().endsWith("EO")) {
            return getClassName().replaceAll("EO", "");
        } else {
            return getClassName();
        }
    }

    public String getClassNameNoEOLowerCamel() {
        return NameUtils.lowerFirstChar(getClassNameNoEO());
    }

    public List<TableFieldGen> getQueryFieldList() {
        List<TableFieldGen> list = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(this.getFieldList())) {
            for (TableFieldGen tableFieldGen : this.getFieldList()) {
                if (tableFieldGen.getIsQuery() == 1) {
                    list.add(tableFieldGen);
                }
            }
        }
        return list;
    }

    public String getQueryFieldStr() {
        StringBuilder queryStr = new StringBuilder();
        List<TableFieldGen> list = getQueryFieldList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                TableFieldGen tableFieldGen = list.get(i);
                if (tableFieldGen.getJavaType().equals("Date")) {
                    queryStr.append("String ").append(tableFieldGen.getJavaFieldName()).append("From, ");
                    queryStr.append("String ").append(tableFieldGen.getJavaFieldName()).append("To");
                } else {
                    queryStr.append("String ").append(tableFieldGen.getJavaFieldName());
                }
                if (i < list.size() - 1) {
                    queryStr.append(", ");
                }
            }

        }
        return queryStr.toString();
    }

    public String getQueryParamStr() {
        StringBuilder queryStr = new StringBuilder();
        List<TableFieldGen> list = getQueryFieldList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                TableFieldGen tableFieldGen = list.get(i);
                if (tableFieldGen.getJavaType().equals("Date")) {
                    queryStr.append(tableFieldGen.getJavaFieldName()).append("From, ");
                    queryStr.append(tableFieldGen.getJavaFieldName()).append("To");
                } else {
                    queryStr.append(tableFieldGen.getJavaFieldName());
                }
                queryStr.append(tableFieldGen.getJavaFieldName());
                if (i < list.size() - 1) {
                    queryStr.append(", ");
                }
            }

        }
        return queryStr.toString();
    }

    public TableFieldGen getIdField() {
        if (CollectionUtils.isEmpty(fieldList)) {
            return null;
        }

        TableFieldGen tableFieldGen = null;
        for (CodeGenTableFieldEO tableField : fieldList) {
            if (tableField.getIsPrimaryKey() == 1) {
                tableFieldGen = ObjectUtils.cloneAs(tableField, TableFieldGen.class);
            }
        }
        return tableFieldGen;
    }
}
