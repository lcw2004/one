package com.lcw.one.bidding.constant;

/**
 * 项目状态 状态
 */
public enum BiddingProjectStatusEnum {

    INVALID(-2, "流标"),
    DELETED(-1, "已删除"),
    NOT_COMMIT(0, "待提交"),
    AUDIT(1, "已提交审核"),
    NORMAL(2, "审核通过(等待开标)"),
    OPEN(3, "开标中"),
    PRE_AWARD(4, "开标结束，预售标"),
    ANNOUNCE(5, "授标结束"),
    ARCHIVE(6, "项目归档");

    private Integer value;
    private String label;

    BiddingProjectStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static BiddingProjectStatusEnum get(Integer value) {
        BiddingProjectStatusEnum[] types = BiddingProjectStatusEnum.values();

        for (BiddingProjectStatusEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
