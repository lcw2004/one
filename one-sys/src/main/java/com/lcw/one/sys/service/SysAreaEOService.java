package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.SysAreaEODao;
import com.lcw.one.sys.entity.SysAreaEO;
import com.lcw.one.sys.entity.SysRoleEO;
import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.service.TreeEntityService;
import org.springframework.stereotype.Service;

@Service
public class SysAreaEOService extends TreeEntityService<SysAreaEODao, SysAreaEO, String> {


    @Override
    public SysAreaEO getTopEntity() {
        return get("1");
    }
}
