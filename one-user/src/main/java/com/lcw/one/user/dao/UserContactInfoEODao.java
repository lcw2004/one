package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserContactInfoEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserContactInfoEODao extends BaseRepositoryImpl<UserContactInfoEO, String> {

    @Autowired
    public UserContactInfoEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserContactInfoEO.class, entityManager), entityManager);
    }

}