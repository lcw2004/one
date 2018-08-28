package com.lcw.one.workflow.bean.constant;

public enum FlowAssignTypeEnum {

    NO_TARGET(1, "无绑定对象"),
    ROLE(2, "角色"),
    OFFICE(3, "机构"),
    USER(4, "用户"),
    OFFICE_AND_ROLE(5, "机构+角色"),
    APPLY_USER(6, "发起人"),
    APPLY_OFFICE(7, "发起人所属机构"),
    APPLY_OFFICE_ROLE(8, "发起人所属机构+角色"),
    BY_CODE(9, "通过代码指定");

    private String label;
    private Integer value;

    FlowAssignTypeEnum(Integer value, String label) {
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
