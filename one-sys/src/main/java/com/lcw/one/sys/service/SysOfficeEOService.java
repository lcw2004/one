package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.SysOfficeEODao;
import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.util.service.TreeEntityService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOfficeEOService extends TreeEntityService<SysOfficeEODao, SysOfficeEO, String> {

    @Autowired
    private SysAreaEOService sysAreaEOService;

    @Override
    public SysOfficeEO getTopEntity() {
        return get("1");
    }

    @Override
    public SysOfficeEO get(String id) {
        SysOfficeEO sysOfficeEO = super.get(id);
        if (StringUtils.isNotEmpty(sysOfficeEO.getAreaId())) {
            sysOfficeEO.setArea(sysAreaEOService.get(sysOfficeEO.getAreaId()));
        }
        return sysOfficeEO;
    }

    /**
     * 获取简单的机构信息（不返回父节点或者子节点的信息）
     * @param id
     * @return
     */
    public SysOfficeEO getSimple(String id) {
        SysOfficeEO sysOfficeEO = ObjectUtils.clone(super.get(id));
        sysOfficeEO.setParent(null);
        return sysOfficeEO;
    }

    @Override
    public SysOfficeEO save(SysOfficeEO sysOfficeEO) {
        sysOfficeEO.setParentId(sysOfficeEO.getParent().getId());
        sysOfficeEO.setAreaId(sysOfficeEO.getArea().getId());
        if (sysOfficeEO.getMasterUserInfo() != null) {
            sysOfficeEO.setMasterId(sysOfficeEO.getMasterUserInfo().getUserId());
        }
        return super.save(sysOfficeEO);
    }

    @Override
    public SysOfficeEO update(SysOfficeEO sysOfficeEO) {
        sysOfficeEO.setParentId(sysOfficeEO.getParent().getId());
        sysOfficeEO.setAreaId(sysOfficeEO.getArea().getId());
        if (sysOfficeEO.getMasterUserInfo() != null) {
            sysOfficeEO.setMasterId(sysOfficeEO.getMasterUserInfo().getUserId());
        }
        return super.update(sysOfficeEO);
    }
}
