package com.lcw.one.util.constant;

public enum BusinessTypeEnum {
    SUPPLIER_APPLY("SUPPLIER_APPLY", "供应商注册");

    private String flowId;
    private String flowName;

    BusinessTypeEnum(String flowId, String flowName) {
        this.flowId = flowId;
        this.flowName = flowName;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getFlowName() {
        return flowName;
    }
}
