package com.lcw.one.util.utils;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @version 2017-04-30.
 * @auth Licw
 */
public class PersistenceUtils {

    public static String fillParams(String hql, Object... params) {
        String hqlFilled = hql;
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                hqlFilled = hqlFilled.replaceAll("\\?" + (i + 1), getReplacement(params[i]));
            }
        }
        return hqlFilled;
    }

    public static String fillParams(String hql, Map<String, Object> params) {
        String hqlFilled = hql;
        if (CollectionUtils.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                hqlFilled = hqlFilled.replaceAll(":" + entry.getKey(), getReplacement(entry.getValue()));
            }
        }
        return hqlFilled;
    }

    private static String getReplacement(Object param) {
        if (param == null) {
            return "";
        }

        String returnValue = "";
        if (param instanceof String) {
            returnValue = "'" + String.valueOf(param) + "'";
        } else if (param instanceof Date) {
            returnValue = DateUtils.dateToString((Date) param, DateUtils.yyyy_MM_dd_HH_mm_ss_EN);
        } else {
            returnValue = String.valueOf(param);
        }
        return Matcher.quoteReplacement(returnValue);
    }
}