package com.lcw.one.ui;

import com.lcw.one.common.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.lcw.one")
@SpringBootApplication
public class OneApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(OneApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OneApplication.class);
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = SpringApplication.run(OneApplication.class, args);
		SpringContextHolder.setApplicationContext(applicationContext);
		logger.info("Registry ApplicationContext");
	}

}