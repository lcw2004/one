package com.lcw.one.sys.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.sys.service.SysDictEOService;
import com.lcw.one.util.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class DictUtils {

    public static final String CACHE_DICT_MAP = "dictMap";
    private static SysDictEOService dictService = SpringContextHolder.getBean(SysDictEOService.class);

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
            for (SysDictEO dict : getDictList(type)) {
                if (type.equals(dict.getType()) && String.valueOf(value).equals(dict.getValue())) {
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
    public static List<SysDictEO> getDictList(String type) {
        List<SysDictEO> dictList = getDictMap().get(type);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    public static Map<String, List<SysDictEO>> getDictMap() {
        // TODO 此处需要加入字段缓存
        Map<String, List<SysDictEO>> dictMap = Maps.newHashMap();
        for (SysDictEO dict : dictService.findAll()) {
            List<SysDictEO> dictList = dictMap.get(dict.getType());
            if (dictList != null) {
                dictList.add(dict);
            } else {
                dictMap.put(dict.getType(), Lists.newArrayList(dict));
            }
        }
        return dictMap;
    }
}