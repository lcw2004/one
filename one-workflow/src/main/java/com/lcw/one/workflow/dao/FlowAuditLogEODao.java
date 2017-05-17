package com.lcw.one.workflow.dao;

import com.lcw.one.workflow.entity.FlowAuditLogEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class FlowAuditLogEODao extends BaseRepositoryImpl<FlowAuditLogEO, String> {

    @Autowired
    public FlowAuditLogEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(FlowAuditLogEO.class, entityManager), entityManager);
    }

}