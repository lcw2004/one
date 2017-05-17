package com.lcw.one.util.utils;

import java.util.Collection;
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
}
