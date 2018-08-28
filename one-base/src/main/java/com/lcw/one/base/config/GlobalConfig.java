package com.lcw.one.base.config;

import com.lcw.one.util.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 添加系统级别缓存
 */
public class GlobalConfig {

    public static String DEFAULT_CHART_SET = "UTF-8";
    private static Map<String, String> systemConfigMap = new HashMap<>();
    private static String restApiPath;
    private static String oneUrl;
    private static Long cookieExpireTime;
    private static Long registryCodeExpireTime;
    private static Integer maxLoginErrorCount;
    private static String companyName;

    public static String getRestApiPath() {
        return restApiPath;
    }

    public static void setRestApiPath(String restApiPath) {
        GlobalConfig.restApiPath = restApiPath;
    }

    public static String getOneUrl() {
        return oneUrl;
    }

    public static void setOneUrl(String oneUrl) {
        GlobalConfig.oneUrl = oneUrl;
    }

    public static Long getCookieExpireTime() {
        return cookieExpireTime;
    }

    public static void setCookieExpireTime(Long cookieExpireTime) {
        GlobalConfig.cookieExpireTime = cookieExpireTime;
    }

    public static Long getRegistryCodeExpireTime() {
        return registryCodeExpireTime;
    }

    public static void setRegistryCodeExpireTime(Long registryCodeExpireTime) {
        GlobalConfig.registryCodeExpireTime = registryCodeExpireTime;
    }

    public static Integer getMaxLoginErrorCount() {
        return maxLoginErrorCount;
    }

    public static void setMaxLoginErrorCount(Integer maxLoginErrorCount) {
        GlobalConfig.maxLoginErrorCount = maxLoginErrorCount;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        GlobalConfig.companyName = companyName;
    }

    public static Map<String, String> getSystemConfigMap() {
        return systemConfigMap;
    }

    public static void setSystemConfigMap(Map<String, String> systemConfigMap) {
        GlobalConfig.systemConfigMap = systemConfigMap;
    }

    public static String getValue(String key) {
        return getValue(key, null);
    }

    public static String getValue(String key, String defaultValue) {
        String value = getSystemConfigMap().get(key);
        return StringUtils.isNotEmpty(value) ? value : defaultValue;
    }

    public static Long getLongValue(String key, Long defaultValue) {
        String value = getSystemConfigMap().get(key);
        return StringUtils.isNotEmpty(value) ? Long.valueOf(value) : defaultValue;
    }

    public static Long getLongValue(String key) {
        return getLongValue(key, null);
    }

    public static Integer getIntegerValue(String key, Integer defaultValue) {
        String value = getSystemConfigMap().get(key);
        return StringUtils.isNotEmpty(value) ? Integer.valueOf(value) : defaultValue;
    }

    public static Integer getIntegerValue(String key) {
        return getIntegerValue(key, null);
    }

    public static Boolean getBooleanValue(String key, Boolean defaultValue) {
        String value = getSystemConfigMap().get(key);
        return StringUtils.isNotEmpty(value) ? "1".equals(value) || "true".equals(value) : defaultValue;
    }

    public static Boolean getBooleanValue(String key) {
        return getBooleanValue(key, null);
    }
}
