package com.lcw.one.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lcw.one")
public class OneTestApplication {

	private static final Logger logger = LoggerFactory.getLogger(OneTestApplication.class);
	
    public static void main(String[] args) {
    	ApplicationContext applicationContext = SpringApplication.run(OneTestApplication.class, args);
		logger.info("Registry ApplicationContext");
    }

}
