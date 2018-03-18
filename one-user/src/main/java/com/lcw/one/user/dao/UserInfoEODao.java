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

    public boolean isExistByAccount(String account) {
        long count = executeGet("select count(1) from UserInfoEO where account = ?1", account);
        return count > 0;
    }

    public void updateUserLoginInfo(String userId, String loginIp) {
        UserInfoEO userInfoEO = getOne(userId);
        userInfoEO.setLastLoginIp(loginIp);
        userInfoEO.setLastLoginTime(new Date());
        saveAndFlush(userInfoEO);
    }

    public UserInfoEO getUserInfoEOByOpenId(String openId) {
        return getByHql("select u from UserInfoEO u, UserContactInfoEO uc where u.userId = uc.userId and uc.wechatId = ?1", openId);
    }

    public UserInfoEO getUserInfoEOByMobile(String mobile) {
        return getByHql("select u from UserInfoEO u, UserContactInfoEO uc where u.userId = uc.userId and uc.mobile = ?1", mobile);
    }

    public void unbindWechat(String userId) {
        executeUpdate("update UserContactInfoEO set wechatId = null where userId = ?1", userId);
    }

    public Integer getMaxDataScope(String userId) {
        return executeGet("select sr.dataScope from SysRoleEO sr, SysUserRoleEO ur where sr.id = ur.roleId and ur.userId = ?1 order by sr.dataScope asc", userId);
    }
}