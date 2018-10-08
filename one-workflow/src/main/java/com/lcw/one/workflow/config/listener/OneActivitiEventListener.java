package com.lcw.one.workflow.config.listener;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.SpringContextHolder;
import com.lcw.one.workflow.audit.IActivitiAssign;
import com.lcw.one.workflow.bean.constant.FlowAssignTypeEnum;
import com.lcw.one.workflow.utils.ThreadLocalContext;
import com.lcw.one.workflow.entity.FlowInfoEO;
import com.lcw.one.workflow.entity.FlowTaskInfoEO;
import com.lcw.one.workflow.service.FlowInfoEOService;
import com.lcw.one.workflow.service.FlowTaskInfoEOService;
import com.lcw.one.workflow.service.WorkFlowAssignService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Activiti事件监听
 * <p>
 * 注意：Listener依赖的Serivce里面不能有Acitiviti相关的依赖，否则初始化的时候相关依赖没有出丝毫，会导致系统启动不了。
 */
@Service
public class OneActivitiEventListener implements ActivitiEventListener {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FlowTaskInfoEOService flowTaskInfoEOService;

    @Autowired
    private FlowInfoEOService flowInfoEOService;

    @Autowired
    private WorkFlowAssignService workFlowAssignService;

    @Override
    public void onEvent(ActivitiEvent event) {
        try {
            switch (event.getType()) {
                case TASK_CREATED:
                    handleTaskCreatedEvent(event);
            }
        } catch (OneBaseException e) {
            logger.error(e.getMessage(), e);
            throwException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throwException(e.getMessage());
        }
    }

    /**
     * 任务创建时候的事件
     * 1.   将任务分配给配置的角色
     *
     * @param event
     */
    private void handleTaskCreatedEvent(ActivitiEvent event) {
        ActivitiEntityEventImpl eventImpl = (ActivitiEntityEventImpl) event;
        TaskEntity taskEntity = (TaskEntity) eventImpl.getEntity();

        String taskName = taskEntity.getName();
        String taskDefinitionKey = taskEntity.getTaskDefinitionKey(); // 流程节点Id
        String processDefinitionKey = taskEntity.getProcessInstance().getProcessDefinitionKey(); // 流程ID
        logger.info("Task Name[{}], TaskDefinitionKey[{}], ProcessDefinitionKey[{}]", taskName, taskDefinitionKey, processDefinitionKey);

        // 获取该节点绑定的表单
        FlowInfoEO flowInfoEO = flowInfoEOService.getAndValid(processDefinitionKey);
        FlowTaskInfoEO flowTaskInfoEO = flowTaskInfoEOService.getFlowTaskInfoEOAndValid(processDefinitionKey, taskDefinitionKey);

        // 设置表单地址
        taskEntity.setFormKey(flowInfoEO.getBindViewForm());
        taskEntity.setVariableLocal("operationForm", flowTaskInfoEO.getBindOperationForm());

        if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.ROLE.getValue()) {
            workFlowAssignService.assignToRole(taskEntity, flowTaskInfoEO.getBindRoleId());
        } else if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.OFFICE.getValue()) {
            workFlowAssignService.assignToOffice(taskEntity, flowTaskInfoEO.getBindOfficeId());
        } else if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.USER.getValue()) {
            workFlowAssignService.assignToUser(taskEntity, flowTaskInfoEO.getBindUserId());
        } else if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.OFFICE_AND_ROLE.getValue()) {
            workFlowAssignService.assignToOfficeAndRole(taskEntity, flowTaskInfoEO.getBindOfficeId(), flowTaskInfoEO.getBindRoleId());
        } else if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.APPLY_USER.getValue()) {
            workFlowAssignService.assignToUser(taskEntity, (String) taskEntity.getVariable("applyUserId"));
        } else if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.APPLY_OFFICE.getValue()) {
            workFlowAssignService.assignToOffice(taskEntity, (String) taskEntity.getVariable("applyOfficeId"));
        } else if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.APPLY_OFFICE_ROLE.getValue()) {
            workFlowAssignService.assignToOfficeAndRole(taskEntity, (String) taskEntity.getVariable("applyOfficeId"), flowTaskInfoEO.getBindRoleId());
        } else if (flowTaskInfoEO.getBindType() == FlowAssignTypeEnum.BY_CODE.getValue()) {
            // 需要在各自的Listener中设置
            IActivitiAssign activitiAssign = SpringContextHolder.getBean(flowTaskInfoEO.getBindService());
            if (activitiAssign == null) {
                throw new OneBaseException("工作流获取服务[" + flowTaskInfoEO.getBindService() + "]失败");
            }
            activitiAssign.assign(taskEntity);
        }
    }

    private static void throwException(String value) {
        Map<String, String> exceptionMap = new HashMap<>();
        exceptionMap.put("cause", value);
        ThreadLocalContext.threadLocal.set(exceptionMap);
        throw new OneBaseException(value);
    }

    @Override
    public boolean isFailOnException() {
        return true;
    }

}
