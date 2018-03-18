package com.lcw.one.notify.bean;

/**
 * 产品状态
 */
public enum MessageTargetTypeEnum {

    TO_USER(-1, "已删除"),
    TO_ROLE(0, "未阅读"),
    TO_OFFICE(1, "已阅读");

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
