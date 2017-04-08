package com.lcw.one.workflow.config.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 引擎的全局监听器
 *
 * @author: zhuwen
 */
@Service
@Transactional
public class VariableCreateListener implements ActivitiEventListener {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onEvent(ActivitiEvent event) {

        switch (event.getType()) {
            case TASK_CREATED:
                handle(event);
        }

    }

    private class ProcessTaskInfoNotDefinitionException extends RuntimeException {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public ProcessTaskInfoNotDefinitionException(String message) {
            super(message);
        }
    }


    public void handle(ActivitiEvent event) {
        ActivitiEntityEventImpl eventImpl = (ActivitiEntityEventImpl) event;
        TaskEntity taskEntity = (TaskEntity) eventImpl.getEntity();

        logger.debug("create task is " + taskEntity.getName() + " key is:" + taskEntity.getTaskDefinitionKey());
        logger.debug("enter the task create listener ---->" + event.getType().name());
        logger.debug("taskEntity.getProcessDefinitionKey() ---->" + taskEntity.getProcessInstance().getProcessDefinitionKey());
    }


    @Override
    public boolean isFailOnException() {
        return false;
    }
}
