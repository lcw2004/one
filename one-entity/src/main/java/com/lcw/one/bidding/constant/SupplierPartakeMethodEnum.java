package com.lcw.one.bidding.constant;

/**
 * 参与方式
 */
public enum SupplierPartakeMethodEnum {

    INVITE(1, "邀请参与"),
    APPLY(2, "报名参与");

    private Integer value;
    private String label;

    SupplierPartakeMethodEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static SupplierPartakeMethodEnum get(Integer value) {
        SupplierPartakeMethodEnum[] types = SupplierPartakeMethodEnum.values();

        for (SupplierPartakeMethodEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
