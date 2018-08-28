package com.lcw.one.sys.constant;

public enum SysFilePermissionTypeEnum {
    INNER(0, "内部访问"),
    OUTER(1, "外部访问");

    private Integer value;
    private String label;

    SysFilePermissionTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

}
