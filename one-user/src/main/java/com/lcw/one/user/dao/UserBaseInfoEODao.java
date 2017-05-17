package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserBaseInfoEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserBaseInfoEODao extends BaseRepositoryImpl<UserBaseInfoEO, String> {

    @Autowired
    public UserBaseInfoEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserBaseInfoEO.class, entityManager), entityManager);
    }

}