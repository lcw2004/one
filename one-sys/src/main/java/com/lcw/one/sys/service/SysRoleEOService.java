package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.SysMenuEODao;
import com.lcw.one.sys.dao.SysRoleEODao;
import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.sys.entity.SysRoleEO;
import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleEOService extends CrudService<SysRoleEODao, SysRoleEO> {

    @Autowired
    private SysMenuEODao sysMenuEODao;

    @Override
    public SysRoleEO save(SysRoleEO sysRoleEO) {
        sysRoleEO.setId(UUID.randomUUID10());
        sysRoleEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        dao.save(sysRoleEO);
        return sysRoleEO;
    }

    @Override
    public SysRoleEO update(SysRoleEO sysRoleEO) {
        dao.save(sysRoleEO);
        return sysRoleEO;
    }

    @Override
    public SysRoleEO get(String id) {
        SysRoleEO sysRoleEO = dao.findOne(id);
        sysRoleEO.setSysMenuEOList(sysMenuEODao.listSysMenuEOByRoleId(id));
        for (SysMenuEO sysMenuEO: sysRoleEO.getSysMenuEOList()) {
            sysRoleEO.getSysMenuEOIdList().add(sysMenuEO.getId());
        }
        return sysRoleEO;
    }

    public List<SysRoleEO> getSysRoleListByUserId(String userId) {
        return dao.getSysRoleListByUserId(userId);
    }

    @Override
    public void delete(String id) {
        dao.deleteLogic(id);
    }

    public PageInfo<SysRoleEO> page(PageInfo pageInfo, String roleName) {
        return dao.page(pageInfo, roleName);
    }

}
