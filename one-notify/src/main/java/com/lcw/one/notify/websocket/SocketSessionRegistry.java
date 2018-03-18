package com.lcw.one.notify.websocket;

import com.lcw.one.util.constant.GlobalConfig;
import com.lcw.one.util.utils.RedisUtil;
import com.lcw.one.util.utils.SpringContextHolder;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SocketSessionRegistry {

    private static final String PREFIX = "_WEB_SOCKET";
    private static RedisUtil redisUtil;

    public SocketSessionRegistry() {
    }

    private static RedisUtil getRedisUtil () {
        if(redisUtil == null) {
            redisUtil = SpringContextHolder.getBean(RedisUtil.class);
        }
        return redisUtil;
    }

    /**
     * 获取sessionId
     *
     * @param userId
     * @return
     */
    public static Set<String> getSessionIdSet(String userId) {
        Set set = getRedisUtil().getSet(userId + PREFIX);
        return set != null ? set : Collections.emptySet();
    }

    /**
     * register session
     *
     * @param userId
     * @param sessionId
     */
    public static void registerSessionId(String userId, String sessionId) {
        Assert.notNull(userId, "User must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        getRedisUtil().appendSetValue(userId + PREFIX, sessionId);
        redisUtil.getRedisTemplate().expire(userId + PREFIX, GlobalConfig.getCookieExpireTime(), TimeUnit.SECONDS);
    }

    public static void unRegisterSessionId(String userId, String sessionId) {
        Assert.notNull(userId, "User Name must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        getRedisUtil().removeSetValue(userId + PREFIX, sessionId);
    }
}