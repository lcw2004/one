package com.lcw.one.workflow.audit;

import com.lcw.one.workflow.entity.FlowAuditItemEO;

public interface IDoAudit {

    /**
     * 审核通过之后的业务逻辑
     * @param flowAuditItemEO
     */
    void doAgree(FlowAuditItemEO flowAuditItemEO);

    /**
     * 审核驳回之后的业务逻辑
     * @param flowAuditItemEO
     */
    void doReject(FlowAuditItemEO flowAuditItemEO);

}
