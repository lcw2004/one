package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysAreaEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class SysAreaEODao extends BaseRepositoryImpl<SysAreaEO, String> {

    @Autowired
    public SysAreaEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysAreaEO.class, entityManager), entityManager);
    }

}