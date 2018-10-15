package com.lcw.one.workflow.config;

import com.lcw.one.workflow.config.listener.OneActivitiEventListener;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.SpringExpressionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 由于Activiti暂时不支持Spring Boot2，因此只能自己注入Bean
 */
@Configuration
@DependsOn("oneActivitiEventListener")
public class ActivitiAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiAutoConfiguration.class);
    private static final String FONT_NAME = "宋体";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private OneActivitiEventListener oneActivitiEventListener;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() {
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);
        configuration.setLabelFontName(FONT_NAME);
        configuration.setActivityFontName(FONT_NAME);
        configuration.setAnnotationFontName(FONT_NAME);
        configuration.setEventListeners(Arrays.asList(oneActivitiEventListener));
        configuration.setExpressionManager(new SpringExpressionManager(applicationContext, configuration.getBeans()));
        return configuration;
    }

    @Bean
    public ProcessEngine processEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

    @Bean
    public RepositoryService repositoryService() {
        return processEngine().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() {
        return processEngine().getRuntimeService();
    }

    @Bean
    public TaskService taskService() {
        return processEngine().getTaskService();
    }

    @Bean
    public IdentityService identityService() {
        return processEngine().getIdentityService();
    }

    @Bean
    public HistoryService historyService() {
        return processEngine().getHistoryService();
    }

    @Bean
    public ManagementService managementService() {
        return processEngine().getManagementService();
    }
    
}
