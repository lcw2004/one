package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.SysOfficeEODao;
import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.util.service.TreeEntityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOfficeEOService extends TreeEntityService<SysOfficeEODao, SysOfficeEO> {

    @Autowired
    private SysAreaEOService sysAreaEOService;

    @Override
    public SysOfficeEO get(String id) {
        SysOfficeEO sysOfficeEO = super.get(id);
        if (StringUtils.isNotEmpty(sysOfficeEO.getAreaId())) {
            sysOfficeEO.setArea(sysAreaEOService.get(sysOfficeEO.getAreaId()));
        }
        return sysOfficeEO;
    }

    @Override
    public SysOfficeEO save(SysOfficeEO sysOfficeEO) {
        sysOfficeEO.setParentId(sysOfficeEO.getParent().getId());
        sysOfficeEO.setAreaId(sysOfficeEO.getArea().getId());
        return super.save(sysOfficeEO);
    }

    @Override
    public SysOfficeEO update(SysOfficeEO sysOfficeEO) {
        sysOfficeEO.setParentId(sysOfficeEO.getParent().getId());
        sysOfficeEO.setAreaId(sysOfficeEO.getArea().getId());
        return super.update(sysOfficeEO);
    }
}
