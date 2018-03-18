package com.lcw.one.util.constant;

public enum YesOrNoEnum {
    NO(0, "否"),
    YES(1, "是");

    private int value;
    private String label;

    YesOrNoEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static YesOrNoEnum valueOf(boolean value) {
        return value ? YES : NO;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
