package com.lcw.one.notify.dao;

import com.lcw.one.user.entity.UserContactInfoEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserContactInfoDao extends BaseRepositoryImpl<UserContactInfoEO, String> {

    @Autowired
    public UserContactInfoDao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserContactInfoEO.class, entityManager), entityManager);
    }

    public List<UserContactInfoEO> listUserContractInfo(List<String> userIdList) {
        return list("from UserContactInfoEO where userId in ?1", userIdList);
    }

}