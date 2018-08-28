package com.lcw.one.base.utils;

import com.google.common.collect.Lists;
import com.lcw.one.base.service.SysInitService;
import com.lcw.one.util.bean.SysDict;
import com.lcw.one.util.utils.SpringContextHolder;
import com.lcw.one.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DictUtils {

    private static final Logger logger = LoggerFactory.getLogger(DictUtils.class);

    private static SysInitService getDictService() {
        return SpringContextHolder.getBean(SysInitService.class);
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
     * 根据字典值获取该值的描述
     *
     * @param label 描述
     * @param type  字典类型
     * @return
     */
    public static String getDictValue(Object label, String type) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(String.valueOf(label))) {
            for (SysDict dict : getDictList(type)) {
                if (String.valueOf(label).equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return null;
    }

    public static Integer getIntDictValue(Object label, String type) {
        String dictValue = getDictValue(label, type);
        return StringUtils.isNotEmpty(dictValue) ? Integer.valueOf(dictValue) : null;
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

    public static Map<String, List<SysDict>> getDictMap() {
        return getDictService().getDictMap();
    }

}