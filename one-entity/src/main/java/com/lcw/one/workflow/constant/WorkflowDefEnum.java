package com.lcw.one.workflow.constant;

public enum WorkflowDefEnum {

    SupplierRegistryApply("SupplierRegistryApply", "供应商注册审核"),
    SupplierPurchaseTypeApply("SupplierPurchaseTypeApply", "供应商投标类别审核"),
    BiddingProjectSupplierApplyAudit("BiddingProjectSupplierApplyAudit", "招标项目供应商报名审核"),
    PlanPublishAudit("PlanPublishAudit", "计划立项审核"),
    BiddingProjectPublishAudit("BiddingProjectPublishAudit", "招标项目发布审核"),
    BiddingProjectAwardAudit("BiddingProjectAwardAudit", "招标项目授标审核"),
    AuctionProjectPublishAudit("AuctionProjectPublishAudit", "竞价项目授标审核"),
    AuctionProjectAwardAudit("AuctionProjectAwardAudit", "竞价项目授标审核"),
    InquireProjectPublishAudit("InquireProjectPublishAudit", "询价项目授标审核"),
    InquireProjectAwardAudit("InquireProjectAwardAudit", "询价项目授标审核"),
    CrmProjectAudit("CrmProjectAudit", "CRM项目审核"),
    CrmProjectPriceAudit("CrmProjectPriceAudit", "CRM项目审核"),
    CrmContractAudit("CrmContractAudit", "CRM合同审核")
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
