package com.lcw.one.workflow.audit;

import com.lcw.one.workflow.entity.FlowAuditItemBean;

public interface IDoAudit {

    /**
     * 审核通过之后的业务逻辑
     *
     * @param flowAuditItemEO
     */
    void doAgree(FlowAuditItemBean flowAuditItemEO);

    /**
     * 审核驳回之后的业务逻辑
     *
     * @param flowAuditItemEO
     */
    void doReject(FlowAuditItemBean flowAuditItemEO);

}
