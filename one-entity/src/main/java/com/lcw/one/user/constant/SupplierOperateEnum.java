package com.lcw.one.user.constant;

/**
 * 供应商操作类别
 */
public enum SupplierOperateEnum {

    REGISTRY("100", "注册"),
    CREATE("101", "创建供应商"),
    START("102", "启用供应商"),
    STOP("103", "停用供应商"),
    DELETE("104", "删除供应商"),
    INQUIRE("105", "添加调查记录"),
    LEVEL_UPDATE("106", "等级调整"),
    IN_BLACK_LIST("107", "移入黑名单"),
    OUT_BLACK_LIST("108", "从黑名单中移除"),
    ;

    private String code;
    private String name;

    SupplierOperateEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static SupplierOperateEnum getByCode(String value) {
        SupplierOperateEnum[] types = SupplierOperateEnum.values();

        for (SupplierOperateEnum type : types) {
            if (type.getCode().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
