package com.lcw.one.util.excel;

import com.lcw.one.util.bean.SysDict;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.RedisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDictCache {

    private static Map<String, List<SysDict>> DICT_MAP = new HashMap<>();
    private static Map<String, String> CACHE = new HashMap<>();

    public static void init(Map<String, List<SysDict>> dictMap) {
        DICT_MAP = dictMap;
    }

    public static boolean isEmpty() {
        return CollectionUtils.isEmpty(DICT_MAP);
    }

    public static String[] labelList(String type) {
        String[] labelArray = null;
        if (DICT_MAP.containsKey(type)) {
            List<SysDict> sysDictList = DICT_MAP.get(type);

            labelArray = new String[sysDictList.size()];
            int index = 0;
            for (SysDict sysDict : sysDictList) {
                labelArray[index++] = sysDict.getLabel();
            }
        }
        return labelArray;
    }

    public static String getLabelCache(String type, String value) {
        String key = "LABEL_" + type + "_" + value;
        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        } else {
            String label = getLabel(type, value);
            CACHE.put(key, label);
            return label;
        }
    }

    public static String getValueCache(String type, String label) {
        String key = "VALUE_" + type + "_" + label;
        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        } else {
            String value = getValue(type, label);
            CACHE.put(key, value);
            return value;
        }
    }

    private static String getLabel(String type, String value) {
        String label = "";
        if (DICT_MAP.containsKey(type)) {
            List<SysDict> sysDictList = DICT_MAP.get(type);
            for (SysDict sysDict : sysDictList) {
                if (sysDict.getValue().equals(value)) {
                    label = sysDict.getLabel();
                    break;
                }
            }
        }
        return label;
    }

    private static String getValue(String type, String label) {
        String value = "";
        if (DICT_MAP.containsKey(type)) {
            List<SysDict> sysDictList = DICT_MAP.get(type);
            for (SysDict sysDict : sysDictList) {
                if (sysDict.getLabel().equals(label)) {
                    value = sysDict.getValue();
                    break;
                }
            }
        }
        return value;
    }
}
