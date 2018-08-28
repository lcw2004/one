package com.lcw.one.util.constant;

/**
 * 删除状态标识
 */
public enum DeleteFlagEnum {
    NORMAL(0, "正常"),
    DELETE(1, "已删除");

    private int value;
    private String label;

    DeleteFlagEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
