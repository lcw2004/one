package com.lcw.one.user.constant;

public enum SupplierEventEnum {
    SUPPLIER_REGISTRY_SUCCESS("SupplierRegistrySuccess", "供应商注册成功"),
    SUPPLIER_PURCHASE_TYPE_AUDIT("SupplierPurchaseTypeAudit", "供应商投标类别审核通过/驳回"),
    SUPPLIER_QUALIFICATION_WILL_EXPIRED("SupplierQualificationWillExpired", "供应商资质即将过期"),
    SUPPLIER_QUALIFICATION_EXPIRED("SupplierQualificationExpired", "供应商资质过期");

    private String eventCode;
    private String eventName;

    SupplierEventEnum(String eventCode, String eventName) {
        this.eventCode = eventCode;
        this.eventName = eventName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getEventName() {
        return eventName;
    }
}
