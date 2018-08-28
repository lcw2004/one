package com.lcw.one.user.dao;

import com.lcw.one.user.bean.ManageUserQueryCondition;
import com.lcw.one.user.constant.UserInfoStatusEnum;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserManagerEODao extends BaseRepositoryImpl<UserManagerEO, String> {

    @Autowired
    public UserManagerEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserManagerEO.class, entityManager), entityManager);
    }

    public List<String> listUserIdByOfficeId(String officeId) {
        return executeList("select userId from UserManagerEO where sysOffice.id = ?1", officeId);
    }

    public List<String> listUserIdByOfficeIdAndRoleId(String officeId, String roleId) {
        return executeList("select ur.userId from UserManagerEO um, SysUserRoleEO ur where ur.userId = um.userId and um.sysOffice.id = ?1 and ur.roleId = ?2", officeId, roleId);
    }

    public PageInfo<UserManagerEO> page(ManageUserQueryCondition userQueryCondition) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" from UserManagerEO u where 1=1 ");
        sql.append(" and u.userInfo.status != ").append(UserInfoStatusEnum.DELETE.getValue());
        userQueryCondition.buildHQL(params, sql);
        sql.append(" order by u.userInfo.createTime desc");
        return page(userQueryCondition.getPageInfo(), sql.toString(), params);
    }

}