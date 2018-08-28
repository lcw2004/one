package com.lcw.one.base.service;

import com.google.common.collect.Maps;
import com.lcw.one.base.config.GlobalConfig;
import com.lcw.one.sys.entity.SysDictDataEO;
import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.util.bean.SysDict;
import com.lcw.one.util.excel.ExcelDictCache;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SysInitService  {

    @Autowired
    private SysInitDao sysInitDao;

    @Cacheable(value = "system_dict", key = "'system_dict'")
    public Map<String, List<SysDict>> getDictMap() {
        Map<String, List<SysDict>> dictMap = Maps.newHashMap();

        List<SysDictEO> sysDictEOList = sysInitDao.listSysDict();
        List<SysDictDataEO> sysDictDataEOList = sysInitDao.listSysDictData();
        Map<String, SysDictEO> sysDictEOMap = ObjectUtils.asMapByFiled(sysDictEOList, "dictId");
        Assert.isTrue(CollectionUtils.isNotEmpty(sysDictEOMap), "sysDictEOMap不能为空");

        for (SysDictDataEO sysDictDataEO : sysDictDataEOList) {
            if (sysDictEOMap.containsKey(sysDictDataEO.getDictId())) {
                SysDictEO sysDictEO = sysDictEOMap.get(sysDictDataEO.getDictId());
                List<SysDict> dictList;
                if (dictMap.containsKey(sysDictEO.getType())) {
                    dictList = dictMap.get(sysDictEO.getType());
                } else {
                    dictList = new LinkedList<>();
                    dictMap.put(sysDictEO.getType(), dictList);
                }
                dictList.add(new SysDict(sysDictDataEO.getLabel(), sysDictDataEO.getValue()));
            }
        }
        return dictMap;
    }

    public void initDict() {
        ExcelDictCache.init(this.getDictMap());
    }

    public void initConfig() {
        Map<String, String> systemConfigMap = sysInitDao.getSysConfigMap();
        if (CollectionUtils.isNotEmpty(systemConfigMap)) {
            GlobalConfig.setSystemConfigMap(systemConfigMap);
            GlobalConfig.setCookieExpireTime(60 * GlobalConfig.getLongValue("system_cookie_expire_time", 30L));
            GlobalConfig.setRegistryCodeExpireTime(60 * GlobalConfig.getLongValue("system_registry_code_expire_time", 30L));
            GlobalConfig.setMaxLoginErrorCount(GlobalConfig.getIntegerValue("system_max_login_error_count", 3));
        }
    }

}