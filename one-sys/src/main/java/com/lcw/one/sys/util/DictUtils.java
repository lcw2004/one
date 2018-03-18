package com.lcw.one.sys.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.sys.service.SysDictEOService;
import com.lcw.one.util.bean.SysDict;
import com.lcw.one.util.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DictUtils {

    private static final Logger logger = LoggerFactory.getLogger(DictUtils.class);

    private static SysDictEOService getDictService() {
        return  SpringContextHolder.getBean(SysDictEOService.class);
    }

    /**
     * 根据字典值获取该值的描述
     *
     * @param value 字典值
     * @param type  字典类型
     * @return
     */
    public static String getDictLabel(Object value, String type) {
        return getDictLabel(value, type, "");
    }

    /**
     * 根据字典值获取该值的描述
     *
     * @param value        字典值
     * @param type         字典类型
     * @param defaultValue 默认值
     * @return
     */
    public static String getDictLabel(Object value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(String.valueOf(value))) {
            for (SysDict dict : getDictList(type)) {
                if (String.valueOf(value).equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    /**
     * 获取该类型对应的字典列表
     *
     * @param type 字典类型
     * @return
     */
    public static List<SysDict> getDictList(String type) {
        List<SysDict> dictList = getDictMap().get(type);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    // ---------------------------------------
    public static Map<String, List<SysDict>> getDictMap() {
        // TODO 此处需要加入缓存
        Map<String, List<SysDict>> dictMap = Maps.newHashMap();
        for (SysDictEO dict : getDictService().findAll()) {
            SysDict sysDict = new SysDict(dict.getLabel(), dict.getValue());
            List<SysDict> dictList = dictMap.get(dict.getType());
            if (dictList != null) {
                dictList.add(sysDict);
            } else {
                dictMap.put(dict.getType(), Lists.newArrayList(sysDict));
            }
        }
        return dictMap;
    }

}