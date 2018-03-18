package com.lcw.one.baseInfo.constant;

public enum CodeSettingEnum {

    GYS("001", "供应商编码"),
    ZB("010", "招标项目编码"),
    JJ("011", "竞价项目编码"),
    XJ("012", "询价项目编码"),
    WZ("020", "物资编码"),
    CONTRACT("030", "合同编码"),
    PLAN("040", "计划编码"),
    CRM_PROJECT("050", "CRM项目编码"),
    CRM_CONTRACT("060", "CRM合同编码")
    ;

    private String value;
    private String label;

    CodeSettingEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static CodeSettingEnum get(String value) {
        CodeSettingEnum[] types = CodeSettingEnum.values();

        for (CodeSettingEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
