package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserEducationInfoEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserEducationInfoEODao extends BaseRepositoryImpl<UserEducationInfoEO, String> {

    @Autowired
    public UserEducationInfoEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserEducationInfoEO.class, entityManager), entityManager);
    }

}