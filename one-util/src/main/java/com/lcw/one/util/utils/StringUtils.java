package com.lcw.one.util.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String listToString(List<String> stringList) {
        if (CollectionUtils.isEmpty(stringList)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            builder.append(stringList.get(i));
            if (i < stringList.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static List<String> stringToList(String string) {
        if (isEmpty(string)) {
            return null;
        }

        return Arrays.asList(string.split(","));
    }

    public static List<Integer> stringToIntList(String string) {
        if (isEmpty(string)) {
            return null;
        }

        List<Integer> integerList = new ArrayList<>();
        String[] stringArray = string.split(",");
        for (int i = 0; i < stringArray.length; i++) {
            String str = stringArray[i];
            if (isNotEmpty(str)) {
                integerList.add(Integer.valueOf(str));
            }
        }
        return integerList;
    }
}
