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

    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (SysDictEO dict : getDictList(type)) {
                if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (SysDictEO dict : getDictList(type)) {
                if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    public static List<SysDictEO> getDictList(String type) {
        List<SysDictEO> dictList = getDictMap().get(type);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    public static Map<String, List<SysDictEO>> getDictMap() {
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