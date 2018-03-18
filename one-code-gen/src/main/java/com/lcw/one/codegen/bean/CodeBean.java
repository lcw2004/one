package com.lcw.one.codegen.bean;

public class CodeBean {

    private String code;
    private String savePath;

    public CodeBean() {
    }

    public CodeBean(String code, String savePath) {
        this.code = code;
        this.savePath = savePath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
