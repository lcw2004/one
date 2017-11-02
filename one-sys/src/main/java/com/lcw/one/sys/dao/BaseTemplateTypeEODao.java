package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.BaseTemplateTypeEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class BaseTemplateTypeEODao extends BaseRepositoryImpl<BaseTemplateTypeEO, String> {

    @Autowired
    public BaseTemplateTypeEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(BaseTemplateTypeEO.class, entityManager), entityManager);
    }

}