package com.lcw.one.workflow.service;

import com.lcw.one.util.utils.UUID;
import com.lcw.one.workflow.entity.FlowAuditItemDetailEO;
import com.lcw.one.workflow.dao.FlowAuditItemDetailEODao;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowAuditItemDetailEOService extends CrudService<FlowAuditItemDetailEODao, FlowAuditItemDetailEO, String> {

    public List<FlowAuditItemDetailEO> listByAuditItemId(String auditItemId) {
        return dao.listByAuditItemId(auditItemId);
    }

    public void save(String auditItemId, List<FlowAuditItemDetailEO> detailList) {
        for (FlowAuditItemDetailEO detailEO: detailList) {
            detailEO.setDetailId(UUID.randomUUID());
            detailEO.setAuditItemId(auditItemId);
        }
        this.save(detailList);
    }

}