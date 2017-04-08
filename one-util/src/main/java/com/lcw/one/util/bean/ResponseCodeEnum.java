package com.lcw.one.util.bean;

public enum ResponseCodeEnum {

    SUCCESS("0"),
    ERROR("-1"),
    VALID_ERROR("1000");

    private String code;

    ResponseCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
