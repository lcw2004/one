package com.lcw.one.codegen.bean;

public class CodeGenInfo {

    private EntityMetaInfo entityMetaInfo;
    private CodeMetaInfo daoCodeMetaInfo;
    private CodeMetaInfo serviceCodeMetaInfo;
    private CodeMetaInfo restCodeMetaInfo;

    private String moduleName;
    private String code;

    public EntityMetaInfo getEntityMetaInfo() {
        return entityMetaInfo;
    }

    public void setEntityMetaInfo(EntityMetaInfo entityMetaInfo) {
        this.entityMetaInfo = entityMetaInfo;
    }

    public CodeMetaInfo getDaoCodeMetaInfo() {
        return daoCodeMetaInfo;
    }

    public void setDaoCodeMetaInfo(CodeMetaInfo daoCodeMetaInfo) {
        this.daoCodeMetaInfo = daoCodeMetaInfo;
    }

    public CodeMetaInfo getServiceCodeMetaInfo() {
        return serviceCodeMetaInfo;
    }

    public void setServiceCodeMetaInfo(CodeMetaInfo serviceCodeMetaInfo) {
        this.serviceCodeMetaInfo = serviceCodeMetaInfo;
    }

    public CodeMetaInfo getRestCodeMetaInfo() {
        return restCodeMetaInfo;
    }

    public void setRestCodeMetaInfo(CodeMetaInfo restCodeMetaInfo) {
        this.restCodeMetaInfo = restCodeMetaInfo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
