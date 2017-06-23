package com.lcw.one.workflow.constant;

public enum WorkflowDefEnum {

    SupplierRegistryApply("SupplierRegistryApply", "供应商注册审核"),
    SupplierPurchaseTypeApply("SupplierPurchaseTypeApply", "供应商投标类别审核"),
    BiddingProjectPublishAudit("BiddingProjectPublishAudit", "招标项目发布审核");

    private String flowName;
    private String flowId;

    WorkflowDefEnum(String flowId, String flowName) {
        this.flowId = flowId;
        this.flowName = flowName;
    }

    public String getFlowName() {
        return flowName;
    }

    public String getFlowId() {
        return flowId;
    }

    public static WorkflowDefEnum getByFlowId(String flowId) {
        WorkflowDefEnum target = null;
        for (WorkflowDefEnum flowEnum : WorkflowDefEnum.values()) {
            if (flowEnum.getFlowId().equals(flowId)) {
                target = flowEnum;
            }
        }
        return target;
    }
}
