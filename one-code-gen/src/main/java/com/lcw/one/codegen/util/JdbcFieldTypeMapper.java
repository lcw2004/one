package com.lcw.one.codegen.util;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class JdbcFieldTypeMapper {
    private static Map<Integer, Class<?>> jdbcTypeMap = new TreeMap();

    static {
        jdbcTypeMap.put(-16, String.class);
        jdbcTypeMap.put(-15, String.class);
        jdbcTypeMap.put(-9, String.class);
        jdbcTypeMap.put(-8, String.class);
        jdbcTypeMap.put(-7, Boolean.class);
        jdbcTypeMap.put(-6, Byte.class);
        jdbcTypeMap.put(-5, Long.class);
        jdbcTypeMap.put(-4, Blob.class);
        jdbcTypeMap.put(-3, Blob.class);
        jdbcTypeMap.put(-2, Blob.class);
        jdbcTypeMap.put(-1, String.class);
        jdbcTypeMap.put(1, String.class);
        jdbcTypeMap.put(2, Double.class);
        jdbcTypeMap.put(3, Double.class);
        jdbcTypeMap.put(4, Integer.class);
        jdbcTypeMap.put(5, Short.class);
        jdbcTypeMap.put(6, Float.class);
        jdbcTypeMap.put(7, Double.class);
        jdbcTypeMap.put(8, Double.class);
        jdbcTypeMap.put(12, String.class);
        jdbcTypeMap.put(16, Boolean.class);
        jdbcTypeMap.put(91, Date.class);
        jdbcTypeMap.put(92, Date.class);
        jdbcTypeMap.put(93, Date.class);
        jdbcTypeMap.put(1111, Object.class);
        jdbcTypeMap.put(2004, Blob.class);
        jdbcTypeMap.put(2005, Clob.class);
        jdbcTypeMap.put(2011, Clob.class);
    }

    public static Class<?> getClassTypeByJdbcType(int jdbcType) {
        return jdbcTypeMap.get(jdbcType);
    }
}