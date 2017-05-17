package com.lcw.one.workflow;

import com.lcw.one.util.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//@SpringBootApplication
public class WorkFlowApplication {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(WorkFlowApplication.class, args);
        SpringContextHolder.setApplicationContext(applicationContext);
        logger.info("Registry ApplicationContext");
    }

}
