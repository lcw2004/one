package com.lcw.one.workflow.dao;

import com.lcw.one.workflow.entity.FlowAuditItemDetailEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlowAuditItemDetailEODao extends BaseRepositoryImpl<FlowAuditItemDetailEO, String> {

    @Autowired
    public FlowAuditItemDetailEODao (EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(FlowAuditItemDetailEO.class, entityManager), entityManager);
    }

    public List<FlowAuditItemDetailEO> listByAuditItemId(String auditItemId) {
        return executeList("from FlowAuditItemDetailEO where auditItemId = ?1 order by attributeName", auditItemId);
    }
    
}