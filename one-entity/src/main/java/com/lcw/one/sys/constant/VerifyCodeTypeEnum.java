package com.lcw.one.sys.constant;

public enum VerifyCodeTypeEnum {

    LOGIN("LOGIN"),
    REGISTRY("REGISTRY"),
    RESET_PASSWORD("RESET_PASSWORD");

    private String code;

    VerifyCodeTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
