package com.lcw.one.bidding.constant;

/**
 * 项目类型
 */
// TODO
public enum BiddingOperateTypeEnum {

    ;

    private Integer value;
    private String label;

    BiddingOperateTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static BiddingOperateTypeEnum get(Integer value) {
        BiddingOperateTypeEnum[] types = BiddingOperateTypeEnum.values();

        for (BiddingOperateTypeEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
