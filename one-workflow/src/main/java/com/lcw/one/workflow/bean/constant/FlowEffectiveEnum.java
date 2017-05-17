package com.lcw.one.workflow.bean.constant;

public enum FlowEffectiveEnum {

    NOT_EFFECTIVE(0, "未生效"),
    EFFECTIVE(1, "已生效");

    private String label;
    private Integer value;

    FlowEffectiveEnum(Integer value, String label) {
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
