package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.BaseTemplateEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class BaseTemplateEODao extends BaseRepositoryImpl<BaseTemplateEO, String> {

    @Autowired
    public BaseTemplateEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(BaseTemplateEO.class, entityManager), entityManager);
    }

    public List<BaseTemplateEO> list(String templateTypeCode) {
        return list("from BaseTemplateEO where code = ?1 order by isDefault desc", templateTypeCode);
    }

    public  long count(String templateTypeCode) {
        return executeGet("select count(1) from BaseTemplateEO where code = ?1", templateTypeCode);
    }
}