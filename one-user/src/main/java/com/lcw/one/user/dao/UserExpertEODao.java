package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserExpertEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserExpertEODao extends BaseRepositoryImpl<UserExpertEO, String> {

    @Autowired
    public UserExpertEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserExpertEO.class, entityManager), entityManager);
    }

}