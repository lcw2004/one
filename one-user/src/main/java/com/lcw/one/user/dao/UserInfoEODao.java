package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Date;

@Component
public class UserInfoEODao extends BaseRepositoryImpl<UserInfoEO, String> {

    @Autowired
    public UserInfoEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserInfoEO.class, entityManager), entityManager);
    }

    public UserInfoEO getUserInfoEOByAccount(String account) {
        return getByHql("from UserInfoEO where account = ?1", account);
    }

    public void updateUserLoginInfo(String userId, String loginIp) {
        UserInfoEO userInfoEO = getOne(userId);
        userInfoEO.setLastLoginIp(loginIp);
        userInfoEO.setLastLoginTime(new Date());
        saveAndFlush(userInfoEO);
    }

}