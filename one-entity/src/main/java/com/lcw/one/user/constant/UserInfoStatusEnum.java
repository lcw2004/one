package com.lcw.one.user.constant;

/**
 * UserInfo 状态
 */
public enum UserInfoStatusEnum {

    STOP(-2, "已停用"),
    DELETE(-1, "已删除"),
    NORMAL(1, "正常"),
    AUDIT(2, "审核中"),
    REJECT(3, "审核驳回");

    private int value;
    private String label;

    UserInfoStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
