package com.lcw.one.msg.constant;

/**
 * 项目状态 状态
 */
public enum MsgAnnounceStatusEnum {

    DELETED(-1, "已删除"),
    DRAFT(0, "未启用"),
    STARTED(1, "已启用"),
    CLOSED(2, "已关闭");

    private Integer value;
    private String label;

    MsgAnnounceStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static MsgAnnounceStatusEnum get(Integer value) {
        MsgAnnounceStatusEnum[] types = MsgAnnounceStatusEnum.values();

        for (MsgAnnounceStatusEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
