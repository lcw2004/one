package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.SysDictDataEODao;
import com.lcw.one.sys.entity.SysDictDataEO;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.UUID;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictDataEOService extends CrudService<SysDictDataEODao, SysDictDataEO, String> {

    public List<SysDictDataEO> list(String dictId) {
        return dao.list(dictId);
    }

    @Override
    @CacheEvict(value = "system_dict", key = "'system_dict'", allEntries = true)
    public SysDictDataEO save(SysDictDataEO entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = "system_dict", key = "'system_dict'", allEntries = true)
    public SysDictDataEO update(SysDictDataEO entity) {
        return super.update(entity);
    }

    @CacheEvict(value = "system_dict", key = "'system_dict'", allEntries = true)
    public void deleteByDictId(String dictId) {
        dao.deleteByDictId(dictId);
    }

    @CacheEvict(value = "system_dict", key = "'system_dict'", allEntries = true)
    public SysDictDataEO save(String dictId, SysDictDataEO sysDictDataEO) {
        sysDictDataEO.setDataId(UUID.randomUUID10());
        sysDictDataEO.setDictId(dictId);
        sysDictDataEO.setSort(dao.getNextSort(dictId));
        return this.save(sysDictDataEO);
    }

}
