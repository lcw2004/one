package com.lcw.one.codegen.util;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class JavaFieldTypeDefaultValueMapper {

    private static Map<String, Object> map = new TreeMap();

    static {
        map.put(String.class.getSimpleName(), "''");
        map.put(Boolean.class.getSimpleName(), false);
        map.put(Double.class.getSimpleName(), 0);
        map.put(Integer.class.getSimpleName(), 0);
        map.put(Short.class.getSimpleName(), 0);
        map.put(Float.class.getSimpleName(), 0);
        map.put(Date.class.getSimpleName(), "''");
    }

    public static Object getValue(String javaType) {
        if (map.containsKey(javaType)) {
            return map.get(javaType);
        } else {
            return null;
        }
    }
}
