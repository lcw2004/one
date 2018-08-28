package com.lcw.one.base.utils;

import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.base.config.GlobalConfig;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.utils.http.CookieUtils;
import com.lcw.one.util.utils.RedisUtil;
import com.lcw.one.util.utils.RequestUtils;
import com.lcw.one.util.utils.SpringContextHolder;
import com.lcw.one.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 当前登录用户工具类
 */
public class LoginUserUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoginUserUtils.class);
    public static final String LOGIN_USER_NEW = "_LOGIN_USER_NEW";

    public static final String LOGIN_USER = "_LOGIN_USER";
    public static final String LOGIN_STATE = "_LOGIN_STATE";

    private static RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
    private static RedisUtil getRedisUtil () {
        if (redisUtil == null) {
            redisUtil = SpringContextHolder.getBean(RedisUtil.class);
        }
        return redisUtil;
    }

    public static String getToken(HttpServletRequest request) {
        return CookieUtils.getAuthToken(request);
    }

    public static String getTokenFromSession(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("token");
    }

    public static boolean checkToken(String token) {
        return getRedisUtil().get(token + LOGIN_USER_NEW) != null;
    }

    /**
     * 模拟系统用户
     * @return
     */
    public static LoginUser getSystemUser() {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId("0");
        return loginUser;
    }

    public static LoginUser getCurrentUser(String cookieValue) {
        LoginUser loginUser = getRedisUtil().get(cookieValue + LOGIN_USER_NEW);
        if (loginUser == null) {
            throw new LoginInvalidException();
        }
        logger.info("CacheValue: CookieValue[{}], UserId[{}]", cookieValue, loginUser.getUserId());
        flushAll(cookieValue);
        return loginUser;
    }

    public static LoginUser getCurrentUser(HttpServletRequest request) {
        LoginUser loginUser = getCurrentUser(CookieUtils.getAuthToken(request));
        loginUser.setIp(RequestUtils.getClientIp(request));
        return loginUser;
    }
    public static String getLoginUserIdWithoutFlush(HttpServletRequest request) {
        return getLoginUserIdWithoutFlush(CookieUtils.getAuthToken(request));
    }

    public static String getLoginUserIdWithoutFlush(String cookieValue) {
        LoginUser loginUser = getRedisUtil().get(cookieValue + LOGIN_USER_NEW);
        if (loginUser == null) {
            throw new LoginInvalidException();
        }
        return loginUser.getUserId();
    }

    public static String getLoginUserIdNoException(HttpServletRequest request) {
        String userId = null;
        try {
            userId = getLoginUserIdWithoutFlush(CookieUtils.getAuthToken(request));
        } catch (LoginInvalidException e) {
            // ignore
        }
        return userId;
    }

    public static String getLoginUserId(String cookieValue) {
        return getCurrentUser(cookieValue).getUserId();
    }

    public static String getLoginUserId(HttpServletRequest request) {
        return getLoginUserId(CookieUtils.getAuthToken(request));
    }

    public static String getLoginSupplierId(String cookieValue) {
        return getCurrentUser(cookieValue).getSupplierId();
    }

    public static String getLoginSupplierId(HttpServletRequest request) {
        return getCurrentUser(request).getSupplierId();
    }

    // ------------ todo
    public static UserInfoEO getLoginUserInfo(String cookieValue) {
        LoginUser loginUser = getCurrentUser(cookieValue);
        UserInfoEO userInfoEO = new UserInfoEO();
        userInfoEO.setUserId(loginUser.getUserId());
        return userInfoEO;
    }


    public static UserInfoEO getLoginUserInfo(HttpServletRequest request) {
        return getLoginUserInfo(CookieUtils.getAuthToken(request));
    }

    public static boolean isSupplier(HttpServletRequest request) {
        return getCurrentUser(request).getUserType() == UserInfoTypeEnum.SUPPLIER.getValue();
    }

    public static boolean isManager(HttpServletRequest request) {
        return getCurrentUser(request).getUserType() == UserInfoTypeEnum.MANAGER.getValue();
    }

    public static boolean isExpert(HttpServletRequest request) {
        return getCurrentUser(request).getUserType() == UserInfoTypeEnum.EXPERT.getValue();
    }

    /**
     * 获取用户在线IP
     * @param userId
     * @return
     */
    public static String getUserOnLineIp(String userId) {
        return getRedisUtil().get(userId + LOGIN_STATE);
    }

    /**
     * 清除登录信息
     *
     * @param sessionId
     */
    public static void clearAll(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            return;
        }
        LoginUser loginUser = getRedisUtil().get(sessionId + LOGIN_USER_NEW);
        if (loginUser == null) {
            return;
        }
        String userId = loginUser.getUserId();
        if (StringUtils.isNotEmpty(userId)) {
            getRedisUtil().remove(userId + LOGIN_STATE, sessionId + LOGIN_USER_NEW);
        }
        if (StringUtils.isNotEmpty(loginUser.getSupplierId())) {
            getRedisUtil().remove(loginUser.getSupplierId() + LOGIN_STATE);
        }
    }

    public static void flushAll(String sessionId) {
        LoginUser loginUser = getRedisUtil().get(sessionId + LOGIN_USER_NEW);
        String supplierId = loginUser.getSupplierId();
        String userId = loginUser.getUserId();
        getRedisUtil().expire(sessionId + LOGIN_USER_NEW, GlobalConfig.getCookieExpireTime());
        getRedisUtil().expire(userId+ LOGIN_STATE, GlobalConfig.getCookieExpireTime());
        getRedisUtil().expire(supplierId + LOGIN_STATE, GlobalConfig.getCookieExpireTime());
    }

    /**
     * 缓存
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        getRedisUtil().set(key, value, GlobalConfig.getCookieExpireTime());
    }

    private static String getCacheValue(String key) {
        String value = getRedisUtil().get(key);
        logger.info("CacheValue: Key[{}], Value[{}]", key, value);
        if (StringUtils.isEmpty(value)) {
            throw new LoginInvalidException();
        }
        return value;
    }
}
