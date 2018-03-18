package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.SysConfigEODao;
import com.lcw.one.sys.entity.SysConfigEO;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysConfigEOService extends CrudService<SysConfigEODao, SysConfigEO, String> {

    public List<SysConfigEO> list(String typeCode) {
        return dao.list(typeCode);
    }

    public Map<String, String> map(String typeCode) {
        List<SysConfigEO> sysConfigEOList = list(typeCode);
        Map<String, String> configs = new HashMap<>();
        if (CollectionUtils.isNotEmpty(sysConfigEOList)) {
            for (SysConfigEO sysConfigEO : sysConfigEOList) {
                configs.put(sysConfigEO.getConfigKey(), sysConfigEO.getConfigValue());
            }
        }
        return configs;
    }

    public void update(String key, String value) {
        dao.update(key, value);
    }

    public void update(Map<String, String> configs) {
        if (CollectionUtils.isEmpty(configs)) {
            return;
        }

        for (Map.Entry<String, String> entry : configs.entrySet()) {
            dao.update(entry.getKey(), entry.getValue());
        }
    }
}
