package com.lcw.one.user.constant;

/**
 * Supplier 等级
 */
public enum UserSupplierLevelEnum {

    UNQUALIFIED(1, "不合格供应商"),
    PROBATIONARY(2, "预备供应商"),
    QUALIFIED(3, "合格供应商"),
    PARTNER(4, "战略合作伙伴");

    private Integer value;
    private String label;

    UserSupplierLevelEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static UserSupplierLevelEnum get(Integer value) {
        UserSupplierLevelEnum[] types = UserSupplierLevelEnum.values();

        for (UserSupplierLevelEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
