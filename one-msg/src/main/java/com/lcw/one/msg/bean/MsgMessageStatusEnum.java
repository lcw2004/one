package com.lcw.one.msg.bean;

/**
 * 产品状态
 */
public enum MsgMessageStatusEnum {

    DELETED(-1, "已删除"),
    UN_READ(0, "未阅读"),
    HAS_READ(1, "已阅读");

    private Integer value;
    private String label;

    MsgMessageStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static MsgMessageStatusEnum get(Integer value) {
        MsgMessageStatusEnum[] types = MsgMessageStatusEnum.values();

        for (MsgMessageStatusEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
