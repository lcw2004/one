package com.lcw.one.codegen.bean;

public class ModuleBean implements Comparable<ModuleBean>{

    private String moduleName;
    private String modulePackage;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModulePackage() {
        return modulePackage;
    }

    public void setModulePackage(String modulePackage) {
        this.modulePackage = modulePackage;
    }

    @Override
    public int compareTo(ModuleBean o) {
        return this.moduleName.compareTo(o.getModuleName());
    }
}
