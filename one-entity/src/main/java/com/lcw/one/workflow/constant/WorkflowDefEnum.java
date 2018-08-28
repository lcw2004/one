package com.lcw.one.workflow.constant;

public enum WorkflowDefEnum {

    PlanPublishAudit("PlanPublishAudit", "计划立项审核"),
    ProjectPublishAudit("ProjectPublishAudit", "采购项目发布审核"),
    ProjectAwardAudit("ProjectAwardAudit", "采购项目授标审核"),
    ProjectSupplierApplyAudit("ProjectSupplierApplyAudit", "采购项目供应商报名审核"),
    ContractAudit("ContractAudit", "采购合同审核"),

    SupplierRegistryApply("SupplierRegistryApply", "供应商准入审核"),
    SupplierInfoChange("SupplierInfoChange", "供应商变更审核"),
    ;

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
