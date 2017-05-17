package com.lcw.one.bidding.constant;

/**
 * 项目类型
 */
public enum SupplierPartakeStatusEnum {

    AUDIT(1, "已报名待审核"),
    PASS(2, "报名审核通过"),
    REJECT(3, "报名审核驳回"),
    SUBMIT(4, "已投标"),
    NOT_SUCCESSFUL(11, "未中标"),
    SUCCESSFUL(12, "已中标"),
    OBSOLETE(13, "已废标");

    private Integer value;
    private String label;

    SupplierPartakeStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static SupplierPartakeStatusEnum get(Integer value) {
        SupplierPartakeStatusEnum[] types = SupplierPartakeStatusEnum.values();

        for (SupplierPartakeStatusEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
