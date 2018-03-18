package com.lcw.one.sys.service;

import com.lcw.one.sys.entity.SysHomeComponentEO;
import com.lcw.one.sys.dao.SysHomeComponentEODao;
import com.lcw.one.sys.entity.SysRoleComponentEO;
import com.lcw.one.util.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysHomeComponentEOService extends CrudService<SysHomeComponentEODao, SysHomeComponentEO, Integer> {

    @Autowired
    private SysRoleComponentEOService sysRoleComponentEOService;

    public List<SysHomeComponentEO> listAll() {
        return dao.listAll();
    }

    public List<String> listByRoleId(String roleId) {
        return dao.listByRoleId(roleId);
    }

    public List<SysHomeComponentEO> listByUserId(String userId) {
        return dao.listByUserId(userId);
    }

    public void save(String roleId, List<String> componentIdList) {
        sysRoleComponentEOService.deleteByRoleId(roleId);

        for (String componentId: componentIdList) {
            SysRoleComponentEO sysRoleComponentEO = new SysRoleComponentEO();
            sysRoleComponentEO.setRoleId(roleId);
            sysRoleComponentEO.setComponentId(componentId);
            sysRoleComponentEOService.save(sysRoleComponentEO);
        }
    }
}