package com.lcw.one.sys.service;

import com.lcw.one.sys.entity.SysHomeComponentEO;
import com.lcw.one.sys.entity.SysRoleComponentEO;
import com.lcw.one.sys.dao.SysRoleComponentEODao;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleComponentEOService extends CrudService<SysRoleComponentEODao, SysRoleComponentEO, Integer> {

    public void deleteByRoleId(String roleId) {
        dao.deleteByRoleId(roleId);
    }

}