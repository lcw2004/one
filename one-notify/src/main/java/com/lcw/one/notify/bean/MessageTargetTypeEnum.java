package com.lcw.one.notify.bean;

/**
 * 产品状态
 */
public enum MessageTargetTypeEnum {

    ALL_USER(1, "全体用户"),
    TO_USER(2, "指定用户"),
    TO_ROLE(3, "拥有指定角色的用户"),
    TO_OFFICE(4, "指定机构下的用户");

    private Integer value;
    private String label;

    MessageTargetTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static MessageTargetTypeEnum get(Integer value) {
        MessageTargetTypeEnum[] types = MessageTargetTypeEnum.values();

        for (MessageTargetTypeEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
