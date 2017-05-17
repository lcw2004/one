package com.lcw.one.codegen.bean;

import com.lcw.one.codegen.util.CodeGenUtil;

public class CodeMetaInfo {

    // 所在包名
    private String packageName;

    // 类全路径
    private String className;

    // 类名
    private String simpleClassName;

    // 作为变量时候得名称
    private String varName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public String getVarName() {
        this.varName = CodeGenUtil.lowerFirstChar(simpleClassName);
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }
}
