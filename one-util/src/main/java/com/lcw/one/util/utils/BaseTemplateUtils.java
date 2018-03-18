package com.lcw.one.util.utils;

import com.lcw.one.util.exception.OneBaseException;

import java.io.IOException;
import java.util.Map;

public class BaseTemplateUtils {

    public static final String TEMPLATE_PREFIX = "BASE_TEMPLATE_";

    public static String fillTemplateByCode(String templateTypeCode, Map<String, Object> placeholders) throws IOException {
        RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
        if (redisUtil == null) {
            throw new OneBaseException("连接缓存服务失败");
        }
        String templateContent = redisUtil.get(TEMPLATE_PREFIX + templateTypeCode);

        return BeetlUtils.fill(templateContent, placeholders);
    }
}
