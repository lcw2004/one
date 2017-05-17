package com.lcw.one.codegen.bean;

import java.util.HashMap;
import java.util.Map;

public class GenClassBean {

    private String genClassSimpleName;
    private String packageName;
    private String code;
    private ClassBean classBean;
    private Map<String, String> extMap = new HashMap<>();

    public String getGenClassSimpleName() {
        return genClassSimpleName;
    }

    public void setGenClassSimpleName(String genClassSimpleName) {
        this.genClassSimpleName = genClassSimpleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ClassBean getClassBean() {
        return classBean;
    }

    public void setClassBean(ClassBean classBean) {
        this.classBean = classBean;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    public void addExtInfo(String key, String value) {
        this.extMap.put(key, value);
    }
}
