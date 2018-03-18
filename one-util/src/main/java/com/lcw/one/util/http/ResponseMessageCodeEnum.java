package com.lcw.one.util.http;

public enum ResponseMessageCodeEnum {

    SUCCESS("0"),
    ERROR("-1"),
    VALID_ERROR("1000"),
    RE_LOGIN("999");

    private String code;

    ResponseMessageCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
