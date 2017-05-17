package com.lcw.one.workflow.bean.constant;

/**
 * 流程状态枚举
 */
public enum ProcessStateEOEnum {

    ADDED(1, "新增"),
    UPDATED(2, "修改"),
    NORMAL(3, "正常");

    private String label;
    private Integer value;

    private ProcessStateEOEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }
}
