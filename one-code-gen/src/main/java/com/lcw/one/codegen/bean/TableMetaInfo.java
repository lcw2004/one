package com.lcw.one.codegen.bean;

import com.lcw.one.codegen.util.NameUtils;
import com.lcw.one.util.utils.CollectionUtils;

import java.util.List;

public class TableMetaInfo {
    private String tableName;
    private String tableNameCn;
    private String className;
    private String packageName;
    private List<TableFieldMetaInfo> tableFieldMetaInfoList;
    private String restPath;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNameCn() {
        return tableNameCn;
    }

    public void setTableNameCn(String tableNameCn) {
        this.tableNameCn = tableNameCn;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<TableFieldMetaInfo> getTableFieldMetaInfoList() {
        return tableFieldMetaInfoList;
    }

    public void setTableFieldMetaInfoList(List<TableFieldMetaInfo> tableFieldMetaInfoList) {
        this.tableFieldMetaInfoList = tableFieldMetaInfoList;
    }

    public String getRestPath() {
        return restPath;
    }

    public void setRestPath(String restPath) {
        this.restPath = restPath;
    }

    /**
     * 是否是联合主键
     */
    public Boolean getIsJointKey() {
        if (CollectionUtils.isEmpty(tableFieldMetaInfoList)) {
            return false;
        }

        int primaryKeyCount = 0;
        for (TableFieldMetaInfo tableFieldMetaInfo : tableFieldMetaInfoList) {
            if (tableFieldMetaInfo.getIsPrimaryKey()) {
                primaryKeyCount++;
            }
        }

        return primaryKeyCount > 1;
    }

}
