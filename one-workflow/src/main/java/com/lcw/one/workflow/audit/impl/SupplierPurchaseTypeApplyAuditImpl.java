package com.lcw.one.workflow.audit.impl;

import com.lcw.one.workflow.audit.IDoAudit;
import com.lcw.one.workflow.dao.FlowAuditItemEODao;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SupplierPurchaseTypeApplyAuditImpl implements IDoAudit {

    private static final Logger logger = LoggerFactory.getLogger(SupplierPurchaseTypeApplyAuditImpl.class);

    @Autowired
    private FlowAuditItemEODao flowAuditItemEODao;

    @Override
    public void doAgree(FlowAuditItemEO flowAuditItemEO) {
        logger.info("doAgree : " + this.getClass().getSimpleName());
    }

    @Override
    public void doReject(FlowAuditItemEO flowAuditItemEO) {
        logger.info("doReject : " + this.getClass().getSimpleName());

    }
}
