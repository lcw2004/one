package com.lcw.one.workflow.audit;


import com.lcw.one.workflow.audit.impl.SupplierPurchaseTypeApplyAuditImpl;
import com.lcw.one.workflow.constant.WorkflowDefEnum;

import java.util.HashMap;
import java.util.Map;

public class WorkflowDefHandlerMapper {

    private static Map<WorkflowDefEnum, Class> handlerMapper = new HashMap<>();

    static {
        handlerMapper.put(WorkflowDefEnum.SupplierPurchaseTypeApply, SupplierPurchaseTypeApplyAuditImpl.class);
    }

    public static Class getHandlerClass(WorkflowDefEnum workflowDefEnum) {
        return handlerMapper.get(workflowDefEnum);
    }
}
