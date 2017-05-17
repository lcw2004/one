package com.lcw.one.file.dao;

import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class SysFileEODao extends BaseRepositoryImpl<SysFileEO, String> {

    @Autowired
    public SysFileEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(SysFileEO.class, entityManager), entityManager);
    }

}