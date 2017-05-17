package com.lcw.one.user.constant;

/**
 * 通用审核状态
 */
public enum AuditStatusEnum {

    STOPED(-2, "已停用"),
    DELETED(-1, "已删除"),
    NOT_COMMIT(0, "未提交审核"),
    AUDIT(1, "审核中"),
    NORMAL(2, "审核通过"),
    REJECT(3, "审核驳回");

    private Integer value;
    private String label;

    AuditStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static AuditStatusEnum get(Integer value) {
        AuditStatusEnum[] types = AuditStatusEnum.values();

        for (AuditStatusEnum type : types) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        return null;
    }
}
