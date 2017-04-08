package com.lcw.one.workflow.config;

import com.lcw.one.workflow.listener.VariableCreateListener;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

public class ActivitiProcessEngineConfigurationConfigurer implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private Environment env;

    @Autowired
    private VariableCreateListener variableCreateListener;

    /**
     * 添加全局监听任务创建事件
     * 如果是UserTask任务 则获取配置后分配相应的任务给用户或角色
     *
     * @return
     */
    public List<ActivitiEventListener> eventListeners() {
        List<ActivitiEventListener> list = new ArrayList<ActivitiEventListener>();
        list.add(variableCreateListener);
        return list;
    }

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setEventListeners(eventListeners());
    }
}
