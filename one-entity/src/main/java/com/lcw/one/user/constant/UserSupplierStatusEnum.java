package com.lcw.one.user.constant;

/**
 * Supplier 状态
 */
public enum UserSupplierStatusEnum {

    STOPED(-2, "已停用"),
    DELETED(-1, "已删除"),
    NOT_COMMIT(0, "未提交审核"),
    AUDIT(1, "审核中"),
    NORMAL(2, "审核通过(正常)"),
    REJECT(3, "审核驳回"),

    BLACKLIST(5, "黑名单");

    private Integer value;
    private String label;

    UserSupplierStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static UserSupplierStatusEnum get(Integer value) {
        UserSupplierStatusEnum[] types = UserSupplierStatusEnum.values();

        for (UserSupplierStatusEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
