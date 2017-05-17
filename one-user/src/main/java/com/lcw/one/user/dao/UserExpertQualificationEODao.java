package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserExpertQualificationEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserExpertQualificationEODao extends BaseRepositoryImpl<UserExpertQualificationEO, String> {

    @Autowired
    public UserExpertQualificationEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserExpertQualificationEO.class, entityManager), entityManager);
    }

}