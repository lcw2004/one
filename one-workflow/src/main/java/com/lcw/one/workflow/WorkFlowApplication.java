package com.lcw.one.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WorkFlowApplication {

    private static final Logger log = LoggerFactory.getLogger(WorkFlowApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(WorkFlowApplication.class, args);
        log.debug("WorkflowApplication ApplicationContext registed");
    }



}
