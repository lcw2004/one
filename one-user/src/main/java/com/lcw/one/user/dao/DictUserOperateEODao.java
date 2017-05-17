package com.lcw.one.user.dao;

import com.lcw.one.user.entity.DictUserOperateEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DictUserOperateEODao extends BaseRepositoryImpl<DictUserOperateEO, String> {

    @Autowired
    public DictUserOperateEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(DictUserOperateEO.class, entityManager), entityManager);
    }

}