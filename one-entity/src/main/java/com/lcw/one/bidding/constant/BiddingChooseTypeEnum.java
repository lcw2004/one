package com.lcw.one.bidding.constant;

public enum BiddingChooseTypeEnum {

    EXPERT_DECISION(1, "专家决标"),
    PRICE_DECISION(2, "价格决标");

    private Integer value;
    private String label;

    BiddingChooseTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static BiddingChooseTypeEnum get(Integer value) {
        BiddingChooseTypeEnum[] types = BiddingChooseTypeEnum.values();

        for (BiddingChooseTypeEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
