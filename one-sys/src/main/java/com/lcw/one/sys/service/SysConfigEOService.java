package com.lcw.one.sys.service;

import com.lcw.one.base.service.SysInitService;
import com.lcw.one.sys.dao.SysConfigEODao;
import com.lcw.one.sys.entity.SysConfigEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysConfigEOService extends CrudService<SysConfigEODao, SysConfigEO, String> {

    @Autowired
    private SysInitService sysInitService;

    public PageInfo<SysConfigEO> page(PageInfo pageInfo, String configName, String configKey) {
        return dao.page(pageInfo, configName, configKey);
    }

    public Map<String, String> map(String typeCode) {
        List<SysConfigEO> sysConfigEOList = dao.list(typeCode);
        Map<String, String> configs = new HashMap<>();
        if (CollectionUtils.isNotEmpty(sysConfigEOList)) {
            for (SysConfigEO sysConfigEO : sysConfigEOList) {
                configs.put(sysConfigEO.getConfigKey(), sysConfigEO.getConfigValue());
            }
        }
        return configs;
    }

    @Override
    @CacheEvict(value = "sysConfig", allEntries = true)
    public SysConfigEO update(SysConfigEO entity) {
        sysInitService.initConfig();
        return super.update(entity);
    }

}
