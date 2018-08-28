package com.lcw.one.user.constant;

public enum VerifyCodeTypeEnum {

    LOGIN("VC_LOGIN", null, "登录验证码"),
    REGISTRY("VC_REGISTRY", "001", "注册验证码"),
    RESET_PASSWORD("VC_RESET_PASSWORD", "002", "重置密码验证码");

    private String code;
    private String templateCode;
    private String name;

    VerifyCodeTypeEnum(String code, String templateCode, String name) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getName() {
        return name;
    }
}
