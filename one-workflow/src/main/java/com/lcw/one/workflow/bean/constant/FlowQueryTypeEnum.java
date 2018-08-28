package com.lcw.one.workflow.bean.constant;

public enum FlowQueryTypeEnum {

    BY_ROLE_AND_ASSIGNEE_ID(1, "通过角色+指定审批人"),
    BY_ASSIGNEE_ID(2, "通过指定审批人"),
    BY_APPLY_USER_ID(3, "通过申请人ID");

    private String label;
    private Integer value;

    FlowQueryTypeEnum(Integer value, String label) {
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
