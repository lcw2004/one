package com.lcw.one.codegen.bean;

import java.util.List;

public class CodeGenStrategy {

    private Integer moduleType;
    private Integer isOverWrite;
    private String moduleName;
    private String moduleSimpleName;
    private String modulePackage;
    private String entityPrefix;
    private String entitySuffix;

    private List<String> tableList;

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getIsOverWrite() {
        return isOverWrite;
    }

    public void setIsOverWrite(Integer isOverWrite) {
        this.isOverWrite = isOverWrite;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleSimpleName() {
        return moduleSimpleName;
    }

    public void setModuleSimpleName(String moduleSimpleName) {
        this.moduleSimpleName = moduleSimpleName;
    }

    public String getModulePackage() {
        return modulePackage;
    }

    public void setModulePackage(String modulePackage) {
        this.modulePackage = modulePackage;
    }

    public List<String> getTableList() {
        return tableList;
    }

    public void setTableList(List<String> tableList) {
        this.tableList = tableList;
    }

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public void setEntitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
    }
}
