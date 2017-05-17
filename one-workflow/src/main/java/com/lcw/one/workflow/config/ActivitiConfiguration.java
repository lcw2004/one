package com.lcw.one.workflow.config;

import com.lcw.one.workflow.config.listener.VariableCreateListener;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivitiConfiguration implements ProcessEngineConfigurationConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiConfiguration.class);
    private static final String FONT_NAME = "宋体";

    @Autowired
    private VariableCreateListener variableCreateListener;

    public List<ActivitiEventListener> eventListeners() {
        logger.info("Add ActivitiEventListener");
        List<ActivitiEventListener> list = new ArrayList<ActivitiEventListener>();
        list.add(variableCreateListener);
        return list;
    }

    @Override
    public void configure(SpringProcessEngineConfiguration configuration) {
        configuration.setLabelFontName(FONT_NAME);
        configuration.setActivityFontName(FONT_NAME);
        configuration.setAnnotationFontName(FONT_NAME);
        configuration.setEventListeners(eventListeners());
    }
}
