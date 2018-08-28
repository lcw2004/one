package com.lcw.one.sys.service;

import com.lcw.one.sys.bean.SysLogQueryCondition;
import com.lcw.one.sys.dao.SysLogEODao;
import com.lcw.one.sys.entity.SysLogEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

@Service
public class SysLogEOService extends CrudService<SysLogEODao, SysLogEO, String> {

    public PageInfo<SysLogEO> page(SysLogQueryCondition queryCondition) {
        return dao.page(queryCondition);
    }

}
