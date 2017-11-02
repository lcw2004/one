package com.lcw.one.main.config;

import com.lcw.one.util.constant.GlobalConfig;
import com.lcw.one.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 初始化静态配置
 */
@Configuration
public class GlobalConfigInitializer implements EnvironmentAware  {

    private static final Logger logger = LoggerFactory.getLogger(GlobalConfigInitializer.class);

    @Override
    public void setEnvironment(Environment env) {
        logger.info("Init global config");
        if (StringUtils.isEmpty(GlobalConfig.getRestApiPath())) {
            GlobalConfig.setRestApiPath(env.getProperty("restPath"));
        }
        GlobalConfig.setCookieExpireTime(60 * 30L);
        GlobalConfig.setRegistryCodeExpireTime(60 * 5L);
        GlobalConfig.setMaxLoginErrorCount(3);
    }


}
