package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserManagerEODao extends BaseRepositoryImpl<UserManagerEO, String> {

    @Autowired
    public UserManagerEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserManagerEO.class, entityManager), entityManager);
    }

}