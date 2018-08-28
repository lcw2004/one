
package com.lcw.one.util.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String DEFAULT_CHAR_SET = "UTF-8";

    public static String safeString(String input) {
        if (isEmpty(input)) {
            return "";
        } else {
            return input;
        }
    }

    public static String listToString(List stringList) {
        return listToString(stringList, ",");
    }

    /**
     * 将字符串数组以指定字符分隔的形式拼接起来
     *
     * @param stringList
     * @return
     */
    public static String listToString(List stringList, String splitChar) {
        if (CollectionUtils.isEmpty(stringList)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            builder.append(stringList.get(i));
            if (i < stringList.size() - 1) {
                builder.append(splitChar);
            }
        }
        return builder.toString();
    }

    /**
     * 将字符串从逗号分隔的地方分隔为String数组
     *
     * @param string
     * @return
     */
    public static List<String> stringToList(String string) {
        if (isEmpty(string)) {
            return null;
        }

        return stringToList(string, ",");
    }

    public static List<String> stringToList(String string, String splitChar) {
        if (isEmpty(string)) {
            return null;
        }

        return Arrays.asList(string.split(splitChar));
    }

    /**
     * 将字符串从逗号分隔的地方分隔为int数组
     *
     * @param string
     * @return
     */
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

    /**
     * 对指定数字补0
     *
     * @param input 待补0数据
     * @param num   位数
     * @return
     */
    public static String fillZero(int input, int num) {
        return String.format("%0" + num + "d", input);
    }


    /**
     * 对Email进行模糊化处理
     *
     * @param email
     * @return
     */
    public static String fuzzyEmail(String email) {
        int index = email.indexOf("@");
        if (index < 0) {
            return email;
        }
        String prefix = email.substring(0, index);
        String suffix = email.substring(index, email.length());
        if (prefix.length() < 3) {
            return "***" + suffix;
        } else {
            return prefix.substring(0, 2) + "***" + suffix;
        }
    }

    /**
     * 限制字符串长度
     *
     * @param input  字符串
     * @param length 长度
     * @return
     */
    public static String limitLength(String input, int length) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }

        if (input.length() > length) {
            return input.substring(0, length);
        }
        return input;
    }

    public static String valueOf(byte[] bytes) {
        try {
            return new String(bytes, DEFAULT_CHAR_SET);
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转为查询字符串
     *
     * @param params
     * @return
     */
    public static String toQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (CollectionUtils.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (i == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 转为查询字符串
     *
     * @param params
     * @return
     */
    public static String toQueryString(Map<String, String> params, String encoding) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        if (CollectionUtils.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String paramName = entry.getKey();
                String paramValue = entry.getValue();
                if (i > 0) {
                    sb.append("&");
                }
                paramValue = encode(encoding, paramValue);
                sb.append(paramName).append("=").append(paramValue);
                i++;
            }
        }
        return sb.toString();
    }

    public static String encode(String value, String encoding) {
        if (isNotEmpty(value)) {
            try {
                value = URLEncoder.encode(value, encoding);
            } catch (UnsupportedEncodingException e) {
                // ignore
            }
        }
        return value;
    }

    public static String html_br(String input) {
        return input.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }

    /**
     * 计算文件比特数
     * @param maxFileSizeStr
     * @return
     */
    public static long calculateFileBites(String maxFileSizeStr) {
        long maxFileSize = 0;
        if (maxFileSizeStr.toUpperCase().endsWith("MB")) {
            int mb = Integer.parseInt(maxFileSizeStr.substring(0, maxFileSizeStr.length() - 2));
            maxFileSize = mb * 1024 * 1024L;
        } else if (maxFileSizeStr.toUpperCase().endsWith("KB")) {
            int kb = Integer.parseInt(maxFileSizeStr.substring(0, maxFileSizeStr.length() - 2));
            maxFileSize = kb * 1024;
        }
        return maxFileSize;
    }

    /**
     * 去空格
     * @param input
     * @return
     */
    public static String trim(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        } else {
            input = input.trim();
            input = input.replaceAll("[\\u00A0]+", ""); // 去除Ascii160的空格
            return input;
        }
    }
}
