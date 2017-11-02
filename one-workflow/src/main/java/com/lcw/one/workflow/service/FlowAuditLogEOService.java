package com.lcw.one.workflow.service;

import com.lcw.one.util.service.CrudService;
import com.lcw.one.workflow.dao.FlowAuditLogEODao;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowAuditLogEOService extends CrudService<FlowAuditLogEODao, FlowAuditLogEO> {

    public List<FlowAuditLogEO> list(String businessType, String businessId, String secondBusinessId) {
        return dao.list(businessType, businessId, secondBusinessId);
    }

    public Long count(String businessType, String businessId) {
        return dao.count(businessType, businessId);
    }
}
