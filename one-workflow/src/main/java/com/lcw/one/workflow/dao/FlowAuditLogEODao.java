package com.lcw.one.workflow.dao;

import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlowAuditLogEODao extends BaseRepositoryImpl<FlowAuditLogEO, String> {

    @Autowired
    public FlowAuditLogEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(FlowAuditLogEO.class, entityManager), entityManager);
    }

    public List<FlowAuditLogEO> list(String businessType, String businessId, String secondBusinessId) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select l from FlowAuditLogEO l, FlowAuditItemEO i where l.auditItemId = i.auditItemId");

        if (StringUtils.isNotEmpty(businessType)) {
            sql.append(" and i.businessType = :businessType");
            params.put("businessType", businessType);
        }
        if (StringUtils.isNotEmpty(businessId)) {
            sql.append(" and i.businessId = :businessId");
            params.put("businessId", businessId);
        }
        if (StringUtils.isNotEmpty(secondBusinessId)) {
            sql.append(" and i.secondBusinessId = :secondBusinessId");
            params.put("secondBusinessId", secondBusinessId);
        }
        sql.append(" order by l.auditTime asc");
        return list(sql.toString(), params);
    }

    public Long count(String businessType, String businessId) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select l from FlowAuditLogEO l, FlowAuditItemEO i where l.auditItemId = i.auditItemId");

        if (StringUtils.isNotEmpty(businessType)) {
            sql.append(" and i.businessType = :businessType");
            params.put("businessType", businessType);
        }
        if (StringUtils.isNotEmpty(businessId)) {
            sql.append(" and i.businessId = :businessId");
            params.put("businessId", businessId);
        }
        return executeGet(sql.toString(), params);
    }
}