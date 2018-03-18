package com.lcw.one.util.persistence;

import com.lcw.one.util.bean.LoginUser;

import java.util.Map;

public class DataScopeUtils {

    public static void dataScope(Map<String, Object> paramMap, StringBuilder hql, LoginUser loginUser, String propName) {
        if (loginUser.getDataScope() == DataScopeEnum.ALL.getValue()) {
            // ignore
        } else if (loginUser.getDataScope() == DataScopeEnum.COMPANY_AND_CHILD.getValue()) {
            hql.append(" and ( ").append(propName).append(".sysOffice.parentIds like :userOfficeParentIds or ").append(propName).append(".sysOffice.id = :userOfficeId ) ");
            paramMap.put("userOfficeParentIds", loginUser.getOfficeParentIds() + loginUser.getOfficeId() + ",%");
            paramMap.put("userOfficeId", loginUser.getOfficeId());
        } else if (loginUser.getDataScope() == DataScopeEnum.COMPANY.getValue()) {
            hql.append(" and ").append(propName).append(".sysOffice.id = :userOfficeId");
            paramMap.put("userOfficeId", loginUser.getOfficeId());
        } else if (loginUser.getDataScope() == DataScopeEnum.DEPARTMENT_AND_CHILD.getValue()) {
            hql.append(" and ( ").append(propName).append(".sysOffice.parentIds like :userOfficeParentIds or ").append(propName).append(".sysOffice.id = :userOfficeId ) ");
            paramMap.put("userOfficeParentIds", loginUser.getOfficeParentIds() + loginUser.getOfficeId() + ",%");
            paramMap.put("userOfficeId", loginUser.getOfficeId());
        } else if (loginUser.getDataScope() == DataScopeEnum.DEPARTMENT.getValue()) {
            hql.append(" and ").append(propName).append(".sysOffice.id = :userOfficeId");
            paramMap.put("userOfficeId", loginUser.getOfficeId());
        } else if (loginUser.getDataScope() == DataScopeEnum.SELF.getValue()) {
            hql.append(" and ").append(propName).append(".userId = :currentUserId");
            paramMap.put("currentUserId", loginUser.getUserId());
        } else if (loginUser.getDataScope() == DataScopeEnum.BY_DETAIL.getValue()) {
            // ignore
        }
    }

}
