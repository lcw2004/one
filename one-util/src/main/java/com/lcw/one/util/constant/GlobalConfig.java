package com.lcw.one.util.constant;

public class GlobalConfig {

    // Api 前缀
    private static String restApiPath;

    /**
     * Cookie失效时间
     */
    private static Long cookieExpireTime = 60 * 30L;

    /**
     * 注册验证码失效时间
     */
    private static Long registryCodeExpireTime = 60 * 30L;

    /**
     * 登录失败次数限制
     */
    private static Integer maxLoginErrorCount = 3;

    public static String getRestApiPath() {
        return restApiPath;
    }

    public static void setRestApiPath(String restApiPath) {
        GlobalConfig.restApiPath = restApiPath;
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
}
