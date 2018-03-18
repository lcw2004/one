package com.lcw.one.workflow.service;

import com.lcw.one.util.service.CrudService;
import com.lcw.one.workflow.dao.FlowAuditLogEODao;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审核日志服务
 */
@Service
public class FlowAuditLogEOService extends CrudService<FlowAuditLogEODao, FlowAuditLogEO, String> {

    public List<FlowAuditLogEO> list(String businessType, String businessId, String secondBusinessId) {
        return dao.list(businessType, businessId, secondBusinessId);
    }

}
