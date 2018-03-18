package com.lcw.one.user.constant;

public enum VerifyCodeTypeEnum {

    LOGIN("VC_LOGIN"),
    REGISTRY("VC_REGISTRY"),
    RESET_PASSWORD("VC_RESET_PASSWORD");

    private String code;

    VerifyCodeTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
