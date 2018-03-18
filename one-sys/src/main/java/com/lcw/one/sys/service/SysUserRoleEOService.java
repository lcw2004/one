package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.SysUserRoleEODao;
import com.lcw.one.sys.entity.SysUserRoleEO;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleEOService extends CrudService<SysUserRoleEODao, SysUserRoleEO, String> {

    public void saveSysUserRole(String userId, List<String> roleIdList) {
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            for (String roleId : roleIdList) {
                saveSysUserRole(userId, roleId);
            }
        }
    }

    public void saveSysUserRole(String userId, String roleId) {
        SysUserRoleEO sysUserRoleEO = new SysUserRoleEO();
        sysUserRoleEO.setRoleId(roleId);
        sysUserRoleEO.setUserId(userId);
        super.save(sysUserRoleEO);
    }

    public void deleteRoleWhereNotIn(String userId, List<String> roleIdList) {
        dao.deleteRoleWhereNotIn(userId, roleIdList);
    }

}
