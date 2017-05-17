package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserSupplierUserEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserSupplierUserEODao extends BaseRepositoryImpl<UserSupplierUserEO, String> {

    @Autowired
    public UserSupplierUserEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserSupplierUserEO.class, entityManager), entityManager);
    }

}