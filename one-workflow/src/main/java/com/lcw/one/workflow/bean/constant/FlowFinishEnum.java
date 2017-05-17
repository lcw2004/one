package com.lcw.one.workflow.bean.constant;

public enum FlowFinishEnum {

    NOT_FINISH(0, "未完结"),
    FINISH(1, "已完结");

    private String label;
    private Integer value;

    FlowFinishEnum(Integer value, String label) {
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
