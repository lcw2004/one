package com.lcw.one.bidding.constant;

/**
 * 项目类型
 */
public enum BiddingProjectTypeEnum {

    INVITE_TENDER(1, "邀请招标"),
    OPEN_TENDER(2, "公开招标"),
    DIRECTIONAL_TENDER(3, "定向招标");

    private Integer value;
    private String label;

    BiddingProjectTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static BiddingProjectTypeEnum get(Integer value) {
        BiddingProjectTypeEnum[] types = BiddingProjectTypeEnum.values();

        for (BiddingProjectTypeEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
