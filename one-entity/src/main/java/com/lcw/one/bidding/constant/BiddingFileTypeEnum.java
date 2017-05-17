package com.lcw.one.bidding.constant;

/**
 * 项目类型
 */
public enum BiddingFileTypeEnum {

    COMPLETE(1, "完整标书文件"),
    TECHNICAL(2, "技术部分"),
    BUSINESS(3, "商务部分");

    private Integer value;
    private String label;

    BiddingFileTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static BiddingFileTypeEnum get(Integer value) {
        BiddingFileTypeEnum[] types = BiddingFileTypeEnum.values();

        for (BiddingFileTypeEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
