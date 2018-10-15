package com.lcw.one.sys.service;

import com.google.gson.reflect.TypeToken;
import com.lcw.one.sys.dao.SysOfficeEODao;
import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.service.TreeEntityService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.util.utils.ObjectUtils;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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
        if (sysOfficeEO != null) {
            if (StringUtils.isNotEmpty(sysOfficeEO.getAreaId())) {
                sysOfficeEO.setArea(sysAreaEOService.get(sysOfficeEO.getAreaId()));
            }
            if (StringUtils.isNotEmpty(sysOfficeEO.getOtherInfo())) {
                Type type = new TypeToken<Map<String, String>>() {}.getType();
                sysOfficeEO.setOtherInfoMap(GsonUtil.fromJson(sysOfficeEO.getOtherInfo(), type));
            }
        }
        return sysOfficeEO;
    }

    @Override
    public List<SysOfficeEO> findAll() {
        return dao.findAll();
    }

    public List<SysOfficeEO> listByName(List<String> officeNameList) {
        return dao.listByName(officeNameList);
    }

    /**
     * 获取简单的机构信息（不返回父节点或者子节点的信息）
     * @param id
     * @return
     */
    public SysOfficeEO getSimple(String id) {
        SysOfficeEO sysOfficeEO = ObjectUtils.clone(super.get(id));
        if (sysOfficeEO != null) {
            sysOfficeEO.setParent(null);
        }
        return sysOfficeEO;
    }

    public void validate(SysOfficeEO sysOfficeEO) {
        SysOfficeEO officeInDb = dao.getByCodeAndNameOfParent(sysOfficeEO.getParentId(), sysOfficeEO.getCode(), null, sysOfficeEO.getId());
        SysOfficeEO parentOffice = sysOfficeEO.getParent();
        if (officeInDb != null) {
            throw new OneBaseException("[" + parentOffice.getName() + "]下已经存在编码为[" + officeInDb.getCode() + "]的子机构了");
        }
        officeInDb = dao.getByCodeAndNameOfParent(sysOfficeEO.getParentId(), null, sysOfficeEO.getName(), sysOfficeEO.getId());
        if (officeInDb != null) {
            throw new OneBaseException("[" + parentOffice.getName() + "]下已经存在名称为[" + officeInDb.getName() + "]的子机构了");
        }
    }

    @Override
    public SysOfficeEO save(SysOfficeEO sysOfficeEO) {
        sysOfficeEO.setParentId(sysOfficeEO.getParent().getId());
        sysOfficeEO.setAreaId(sysOfficeEO.getArea().getId());
        if (sysOfficeEO.getMasterUserInfo() != null) {
            sysOfficeEO.setMasterId(sysOfficeEO.getMasterUserInfo().getUserId());
        }
        if (CollectionUtils.isNotEmpty(sysOfficeEO.getOtherInfoMap())) {
            sysOfficeEO.setOtherInfo(GsonUtil.t2Json(sysOfficeEO.getOtherInfoMap()));
        }
        validate(sysOfficeEO);
        return super.save(sysOfficeEO);
    }

    @Override
    public SysOfficeEO update(SysOfficeEO sysOfficeEO) {
        sysOfficeEO.setParentId(sysOfficeEO.getParent().getId());
        sysOfficeEO.setAreaId(sysOfficeEO.getArea().getId());
        if (sysOfficeEO.getMasterUserInfo() != null) {
            sysOfficeEO.setMasterId(sysOfficeEO.getMasterUserInfo().getUserId());
        }
        if (CollectionUtils.isNotEmpty(sysOfficeEO.getOtherInfoMap())) {
            sysOfficeEO.setOtherInfo(GsonUtil.t2Json(sysOfficeEO.getOtherInfoMap()));
        }
        validate(sysOfficeEO);
        return super.update(sysOfficeEO);
    }
}
