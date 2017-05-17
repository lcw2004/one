package com.lcw.one.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.lcw.one.util.utils.SpringContextHolder;

@SpringBootApplication
@ComponentScan("com.lcw.one")
public class OneMainApplication {

	private static final Logger logger = LoggerFactory.getLogger(OneMainApplication.class);
	
    public static void main(String[] args) {
    	ApplicationContext applicationContext = SpringApplication.run(OneMainApplication.class, args);
		SpringContextHolder.setApplicationContext(applicationContext);
		logger.info("Registry ApplicationContext");
    }
}
