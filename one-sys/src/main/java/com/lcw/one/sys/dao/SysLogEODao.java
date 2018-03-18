package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.SysLogEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class SysLogEODao extends BaseRepositoryImpl<SysLogEO, String> {

    @Autowired
    public SysLogEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysLogEO.class, entityManager), entityManager);
    }

}