package com.lcw.one.codegen.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

public class NameUtils {

    public static String lowerHyphenToLowerCamel(String name) {
        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, name);
    }

    public static String toLowerCamel(String name) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }

    public static String toUpperCamel(String name) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    public static String lowerFirstChar(String className) {
        if (StringUtils.isEmpty(className)) {
            return "";
        }

        String firstCharLower = String.valueOf(className.charAt(0)).toLowerCase();
        return firstCharLower + className.substring(1, className.length());
    }

    public static String upperFirstChar(String className) {
        if (StringUtils.isEmpty(className)) {
            return "";
        }

        String firstCharLower = String.valueOf(className.charAt(0)).toUpperCase();
        return firstCharLower + className.substring(1, className.length());
    }

}
