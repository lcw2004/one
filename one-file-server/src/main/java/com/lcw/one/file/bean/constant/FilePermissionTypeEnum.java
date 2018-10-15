package com.lcw.one.file.bean.constant;

public enum FilePermissionTypeEnum {

    INNER(0, "内部访问"),
    OUTER(1, "外部访问");

    private Integer value;
    private String label;

    FilePermissionTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

}
