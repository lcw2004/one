package com.lcw.one.util.utils;

import java.util.Map;

/**
 * @version 2017-04-30.
 * @auth Licw
 */
public class PersistenceUtils {

    public static String fillParams(String hql, Object... params) {
        String hqlFilled = hql;
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                hqlFilled = hqlFilled.replaceAll("\\?" + (i + 1), "'" + params[i] + "'");
            }
        }

        return hqlFilled;
    }


    public static String fillParams(String hql, Map<String, Object> params) {
        String hqlFilled = hql;
        if (CollectionUtils.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                hqlFilled = hqlFilled.replaceAll(":" + key, "'" + params.get(key) + "'");
            }
        }

        return hqlFilled;
    }
}


