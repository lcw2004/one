package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserExpertPurchaseTypeRelationEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserExpertPurchaseTypeRelationEODao extends BaseRepositoryImpl<UserExpertPurchaseTypeRelationEO, String> {

    @Autowired
    public UserExpertPurchaseTypeRelationEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserExpertPurchaseTypeRelationEO.class, entityManager), entityManager);
    }

}