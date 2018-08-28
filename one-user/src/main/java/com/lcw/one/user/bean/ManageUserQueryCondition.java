package com.lcw.one.user.bean;

import com.lcw.one.util.utils.StringUtils;

import java.util.Map;

/**
 * @version 2017-05-18.
 * @auth Licw
 */
public class ManageUserQueryCondition extends UserQueryCondition {

    private String officeId;
    private String roleIdList;

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(String roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public void buildHQL(Map<String, Object> paramMap, StringBuilder hql) {
        super.buildHQL(paramMap, hql);

        if (StringUtils.isNotEmpty(getOfficeId())) {
            hql.append(" and u.officeId = :officeId ");
            paramMap.put("officeId", getOfficeId());
        }

        if (StringUtils.isNotEmpty(getRoleIdList())) {
            hql.append(" and u.userId in (select userId from SysUserRoleEO where roleId in :roleIdList) ");
            paramMap.put("roleIdList", StringUtils.stringToList(getRoleIdList()));
        }
    }
}
