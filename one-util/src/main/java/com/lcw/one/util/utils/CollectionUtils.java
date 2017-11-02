package com.lcw.one.util.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CollectionUtils {

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean isEmpty(Collection collection) {
        return !isNotEmpty(collection);
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    public static boolean isEmpty(Map collection) {
        return !isNotEmpty(collection);
    }

    public static <T> boolean isNotEmpty(T[] t) {
        return t != null && t.length > 0;
    }

    public static <T> boolean isEmpty(T[] t) {
        return !isNotEmpty(t);
    }

    public static <K, V> Map<K, V> clone(Map<K, V> map) {
        Map<K, V> outMap = null;
        if (map.getClass().getSimpleName().equals("HashMap")) {
            outMap = new HashMap<>();
        } else if (map.getClass().getSimpleName().equals("LinkedHashMap")) {
            outMap = new LinkedHashMap<>();
        }

        if (isEmpty(map)) {
            return outMap;
        }

        for (Map.Entry<K, V> entry : map.entrySet()) {
            outMap.put(entry.getKey(), entry.getValue());
        }
        return outMap;
    }
}
