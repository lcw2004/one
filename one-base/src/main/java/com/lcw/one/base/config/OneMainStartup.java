package com.lcw.one.base.config;

import com.lcw.one.base.service.SysInitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class OneMainStartup implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(OneMainStartup.class);

    @Autowired
    private SysInitService sysInitService;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("install excel dict");
        sysInitService.initDict();

        logger.info("install system config");
        sysInitService.initConfig();
    }

}
