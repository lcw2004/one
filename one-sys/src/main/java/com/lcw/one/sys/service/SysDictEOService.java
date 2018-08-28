package com.lcw.one.sys.service;

import com.google.common.collect.Maps;
import com.lcw.one.sys.dao.SysDictEODao;
import com.lcw.one.sys.entity.SysDictDataEO;
import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.util.bean.SysDict;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SysDictEOService extends CrudService<SysDictEODao, SysDictEO, String> {

    @Autowired
    private SysDictDataEOService sysDictDataEOService;

    public PageInfo<SysDictEO> page(PageInfo pageInfo, String type, String name) {
        return dao.page(pageInfo, type, name);
    }

    public List<String> listDictType() {
        return dao.listDictType();
    }

    @Override
    @CacheEvict(value = "system_dict", key = "'system_dict'", allEntries = true)
    public SysDictEO update(SysDictEO entity) {
        return super.update(entity);
    }

    @Override
    @CacheEvict(value = "system_dict", key = "'system_dict'", allEntries = true)
    public List<SysDictEO> update(List<SysDictEO> entityList) {
        return super.update(entityList);
    }

    @Override
    @CacheEvict(value = "system_dict", key = "'system_dict'", allEntries = true)
    public void delete(String s) {
        super.delete(s);
        sysDictDataEOService.deleteByDictId(s);
    }
}
