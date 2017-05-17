package com.lcw.one.sys.service;

import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.sys.dao.SysDictEODao;
import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictEOService extends CrudService<SysDictEODao, SysDictEO> {

    public PageInfo<SysDictEO> page(PageInfo pageInfo, String type, String description) {
        return dao.page(pageInfo, type, description);
    }

    public List<String> listDictType() {
        return dao.listDictType();
    }
}
