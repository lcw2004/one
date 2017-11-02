package com.lcw.one.user.dao;

import com.lcw.one.user.bean.ManageUserQueryCondition;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserManagerEODao extends BaseRepositoryImpl<UserManagerEO, String> {

    @Autowired
    public UserManagerEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserManagerEO.class, entityManager), entityManager);
    }

    public PageInfo<UserManagerEO> page(ManageUserQueryCondition userQueryCondition) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" from UserManagerEO where 1=1 ");
        if (StringUtils.isNotEmpty(userQueryCondition.getOfficeId())) {
            sql.append(" and officeId = :officeId ");
            params.put("officeId", userQueryCondition.getOfficeId());
        }
        if (StringUtils.isNotEmpty(userQueryCondition.getUserLikeName())) {
            sql.append(" and userInfoEO.name like :userLikeName ");
            params.put("userLikeName", "%" + userQueryCondition.getUserLikeName() + "%");
        }
        if (StringUtils.isNotEmpty(userQueryCondition.getUserAccount())) {
            sql.append(" and userInfoEO.userContactInfo.account like :userAccount ");
            params.put("userAccount", "%" + userQueryCondition.getUserAccount() + "%");
        }
        if (StringUtils.isNotEmpty(userQueryCondition.getUserMobile())) {
            sql.append(" and userInfoEO.userContactInfo.mobile like :userMobile ");
            params.put("userMobile", "%" + userQueryCondition.getUserMobile() + "%");
        }
        if (StringUtils.isNotEmpty(userQueryCondition.getUserEmail())) {
            sql.append(" and userInfoEO.userContactInfo.email like :userEmail ");
            params.put("userEmail", "%" + userQueryCondition.getUserEmail() + "%");
        }
        if (StringUtils.isNotEmpty(userQueryCondition.getUserPhone())) {
            sql.append(" and userInfoEO.userContactInfo.phone like :userPhone ");
            params.put("userPhone", "%" + userQueryCondition.getUserPhone() + "%");
        }
        if (StringUtils.isNotEmpty(userQueryCondition.getUserWechatId())) {
            sql.append(" and userInfoEO.userContactInfo.wechatId = :userWechatId ");
            params.put("userWechatId", userQueryCondition.getUserWechatId());
        }
        if (StringUtils.isNotEmpty(userQueryCondition.getUserIdentityNumber())) {
            sql.append(" and userInfoEO.identityNumber like :identityNumber ");
            params.put("identityNumber", "%" + userQueryCondition.getUserIdentityNumber() + "%");
        }
        return page(userQueryCondition.getPageInfo(), sql.toString(), params);
    }

}