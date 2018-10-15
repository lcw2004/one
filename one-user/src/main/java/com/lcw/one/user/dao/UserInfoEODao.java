package com.lcw.one.user.dao;

import com.lcw.one.user.constant.UserInfoStatusEnum;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserInfoEODao extends BaseRepositoryImpl<UserInfoEO, String> {

    @Autowired
    public UserInfoEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserInfoEO.class, entityManager), entityManager);
    }

    public UserInfoEO getUserInfoEOByAccount(String account) {
        return getByHql("from UserInfoEO where account = ?1 and status != -1", account);
    }

    public UserInfoEO getUserInfoEOByAccount(Integer userType, String account) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append("from UserInfoEO where account = :account ");
        paramMap.put("account", account);
        if (userType != null) {
            hql.append(" and type = :userType");
            paramMap.put("userType", userType);
        }
        hql.append(" and status != ").append(UserInfoStatusEnum.DELETE.getValue());
        return getByHql(hql.toString(), paramMap);
    }

    public List<UserInfoEO> listByAccount(List<String> accountList, Integer userType) {
        return list("from UserInfoEO where account in ?1 and type = ?2 and status != " + UserInfoStatusEnum.DELETE.getValue(), accountList, userType);
    }

    public boolean isExistByAccount(Integer userType, String account, String excludeUserId) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" select count(1) from UserInfoEO where account = :account ");
        paramMap.put("account", account);
        if (userType != null) {
            hql.append(" and type = :userType");
            paramMap.put("userType", userType);
        }
        if (StringUtils.isNotEmpty(excludeUserId)) {
            hql.append(" and userId != :excludeUserId");
            paramMap.put("excludeUserId", excludeUserId);
        }
        hql.append(" and status != ").append(UserInfoStatusEnum.DELETE.getValue());
        long count = executeGet(hql.toString(), paramMap);
        return count > 0;
    }

    public boolean isExistByMobile(String mobile, String excludeUserId) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" select count(u) from UserInfoEO u where u.userContactInfo.mobile = :mobile ");
        paramMap.put("mobile", mobile);
        if (StringUtils.isNotEmpty(excludeUserId)) {
            hql.append(" and u.userId != :excludeUserId");
            paramMap.put("excludeUserId", excludeUserId);
        }
        hql.append(" and u.status != ").append(UserInfoStatusEnum.DELETE.getValue());
        long count = executeGet(hql.toString(), paramMap);
        return count > 0;
    }

    public void updateLoginIp(String userId, String loginIp) {
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

    public void updateStatus(String userId, Integer newStatus) {
        executeUpdate("update UserInfoEO set status = ?1 where userId = ?2", newStatus, userId);
    }

    public void updatePassword(String userId, String password) {
        executeUpdate("update UserInfoEO set password = ?1 where userId = ?2", password, userId);
    }

    public Integer getMaxDataScope(String userId) {
        return executeGet("select sr.dataScope from SysRoleEO sr, SysUserRoleEO ur where sr.id = ur.roleId and ur.userId = ?1 order by sr.dataScope asc", userId);
    }
}