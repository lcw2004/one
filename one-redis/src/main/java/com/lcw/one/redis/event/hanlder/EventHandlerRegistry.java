package com.lcw.one.redis.event.hanlder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 时间注册器
 */
public class EventHandlerRegistry {

    private static final Logger logger = LoggerFactory.getLogger(EventHandlerRegistry.class);
    private static ConcurrentMap<String, Class> handlerMap;

    static {
        handlerMap = new ConcurrentHashMap<>();
    }

    public static void registerEventHandler(String eventCode, Class handlerClass) {
        logger.info("Regsiter EventHandler, {} => {}", eventCode, handlerClass.getName());
        handlerMap.put(eventCode, handlerClass);
    }

    public static Class getHandlerClass(String eventCode) {
        return handlerMap.get(eventCode);
    }
}
