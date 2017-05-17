package com.lcw.one.workflow.service;

import com.lcw.one.user.constant.AuditStatusEnum;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.workflow.bean.constant.FlowEffectiveEnum;
import com.lcw.one.workflow.bean.constant.FlowFinishEnum;
import com.lcw.one.workflow.dao.FlowAuditItemEODao;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FlowAuditItemEOService extends CrudService<FlowAuditItemEODao, FlowAuditItemEO> {

    /**
     * 修改状态为驳回
     *
     * @param auditItemId 审核事项ID
     */
    public void updateStateWhenReject(String auditItemId, String remark) {
        FlowAuditItemEO flowAuditItemEO = dao.findOne(auditItemId);
        flowAuditItemEO.setIsFinished(FlowFinishEnum.FINISH.getValue());
        flowAuditItemEO.setIsEffective(FlowEffectiveEnum.NOT_EFFECTIVE.getValue());
        flowAuditItemEO.setAuditStatus(AuditStatusEnum.REJECT.getValue());
        flowAuditItemEO.setRemark(remark);
        update(flowAuditItemEO);
    }

    /**
     * 修改状态为通过
     *
     * @param auditItemId 审核事项ID
     */
    public void updateStateWhenPass(String auditItemId) {
        FlowAuditItemEO flowAuditItemEO = dao.findOne(auditItemId);
        flowAuditItemEO.setIsFinished(FlowFinishEnum.FINISH.getValue());
        flowAuditItemEO.setIsEffective(FlowEffectiveEnum.EFFECTIVE.getValue());
        flowAuditItemEO.setEffectTime(new Date());
        flowAuditItemEO.setAuditStatus(AuditStatusEnum.NORMAL.getValue());
        flowAuditItemEO.setRemark("");
        update(flowAuditItemEO);
    }

}
