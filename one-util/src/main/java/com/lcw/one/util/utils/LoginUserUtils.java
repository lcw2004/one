package com.lcw.one.util.utils;

import com.lcw.one.util.constant.GlobalConfig;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.http.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LoginUserUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoginUserUtils.class);
    public static final String LOGIN_USER = "_LOGIN_USER";
    public static final String LOGIN_USER_ID = "_LOGIN_USER_ID";
    public static final String LOGIN_SUPPLIER_ID = "_LOGIN_SUPPLIER_ID";
    public static final String LOGIN_SUPPLIER = "_LOGIN_SUPPLIER";
    public static final String LOGIN_USER_NAME = "_LOGIN_USER_NAME";
    public static final String LOGIN_ROLE_ID = "_LOGIN_ROLE_ID";

    private static RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);

    public static String getLoginUserId(String cookieValue) {
        return getCacheValue(cookieValue+ LOGIN_USER_ID);
    }

    public static String getLoginSupplierId(String cookieValue) {
        return getCacheValue(cookieValue + LOGIN_SUPPLIER_ID);
    }

    public static String getLoginRoleId(String cookieValue) {
        return getCacheValue(cookieValue + LOGIN_ROLE_ID);
    }

    public static String getLoginUserName(String cookieValue) {
        return getCacheValue(cookieValue + LOGIN_USER_NAME);
    }

    public static String getLoginUserId(HttpServletRequest request) {
        return getLoginUserId(CookieUtils.getCookieValue(request));
    }

    public static String getLoginSupplierId(HttpServletRequest request) {
        return getLoginSupplierId(CookieUtils.getCookieValue(request));
    }

    public static String getLoginRoleId(HttpServletRequest request) {
        return getLoginRoleId(CookieUtils.getCookieValue(request));
    }

    public static String getLoginUserName(HttpServletRequest request) {
        return getLoginUserName(CookieUtils.getCookieValue(request));
    }

    /**
     * 清除登录信息
     * @param sessionId
     */
    public static void clearAll(String sessionId) {
        redisUtil.remove(sessionId + LOGIN_USER,
                sessionId + LOGIN_USER_ID,
                sessionId + LOGIN_USER_NAME,
                sessionId + LOGIN_ROLE_ID,
                sessionId + LOGIN_SUPPLIER_ID,
                sessionId + LOGIN_SUPPLIER);
    }

    /**
     * 缓存
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        redisUtil.set(key, value, GlobalConfig.getCookieExpireTime());
    }

    private static String getCacheValue(String key) {
        String value = redisUtil.get(key);
        logger.info("CacheValue: Key{}, Value{}", key, value);
        if (StringUtils.isEmpty(value)) {
            throw new LoginInvalidException();
        }
        redisUtil.expire(key, GlobalConfig.getCookieExpireTime());
        return value;
    }
}
