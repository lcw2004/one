package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.BaseArchiveMenuEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class BaseArchiveMenuEODao extends BaseRepositoryImpl<BaseArchiveMenuEO, String> {

    @Autowired
    public BaseArchiveMenuEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(BaseArchiveMenuEO.class, entityManager), entityManager);
    }

}