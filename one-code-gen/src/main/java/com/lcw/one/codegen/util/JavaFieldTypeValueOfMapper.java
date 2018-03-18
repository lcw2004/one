package com.lcw.one.codegen.util;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class JavaFieldTypeValueOfMapper {

    private static Map<String, String> map = new TreeMap();

    static {
        map.put(String.class.getSimpleName(), "{0}");
        map.put(Boolean.class.getSimpleName(), "Boolean.valueOf({0})");
        map.put(Double.class.getSimpleName(), "Double.valueOf({0})");
        map.put(Integer.class.getSimpleName(), "Integer.valueOf({0})");
        map.put(Short.class.getSimpleName(), "Short.valueOf({0})");
        map.put(Float.class.getSimpleName(), "Float.valueOf({0})");
        map.put(Date.class.getSimpleName(), "DateUtils.stringToDate({0}, DateUtils.yyyy_MM_dd_EN)");
    }

    public static String getValue(String javaType) {
        if (map.containsKey(javaType)) {
            return map.get(javaType);
        } else {
            return null;
        }
    }

}
