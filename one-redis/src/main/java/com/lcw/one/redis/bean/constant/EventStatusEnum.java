package com.lcw.one.redis.bean.constant;

public enum EventStatusEnum {

    NOT_DONE(0, "未处理"),
    DOING(1, "已处理"),
    DONE(2, "已处理"),
    ERROR(3, "异常");

    private Integer value;
    private String label;

    EventStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static EventStatusEnum get(Integer value) {
        EventStatusEnum[] types = EventStatusEnum.values();

        for (EventStatusEnum type : types) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        return null;
    }
}
