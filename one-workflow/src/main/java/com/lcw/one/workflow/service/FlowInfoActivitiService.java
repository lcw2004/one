package com.lcw.one.workflow.service;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.workflow.entity.FlowInfoEO;
import com.lcw.one.workflow.entity.FlowTaskInfoEO;
import com.lcw.one.workflow.service.flow.ActivitiService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlowInfoActivitiService {

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FlowInfoEOService flowInfoEOService;

    @Autowired
    private FlowTaskInfoEOService flowTaskInfoEOService;

    /**
     * 项目流程图
     * @param processKey
     * @return
     */
    public InputStream progressImage(String processKey) {
        FlowInfoEO flowInfoEO = flowInfoEOService.get(processKey);
        if (flowInfoEO == null) {
            throw new OneBaseException("流程" + processKey + "不存在");
        }
        return activitiService.viewProcessImage(flowInfoEO.getProcessDefinitionId());
    }


    /**
     * 部署工作流
     * @param is
     * @param fileName
     */
    public void deploy(InputStream is, String fileName) {
        List<ProcessDefinition> processDefinitionList = activitiService.deploy(is, fileName);

        for (ProcessDefinition processDefinition : processDefinitionList) {
            FlowInfoEO flowInfoEO = flowInfoEOService.get(processDefinition.getKey());
            if (flowInfoEO == null) {
                flowInfoEO = new FlowInfoEO();
                flowInfoEO.setProcessKey(processDefinition.getKey());
            }
            flowInfoEO.setProcessDefinitionId(processDefinition.getId());
            flowInfoEO.setProcessName(processDefinition.getName());
            flowInfoEO.setLastUpdateTime(new Date());
            flowInfoEOService.save(flowInfoEO);
            saveFlowTaskInfo(processDefinition);
        }
    }

    /**
     * 保存工作流节点信息
     * @param processDefinition
     */
    private void saveFlowTaskInfo(ProcessDefinition processDefinition) {
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinition.getId());
        List<ActivityImpl> activityList = processDefinitionEntity.getActivities();

        List<String> taskKeyList = new ArrayList<>();
        for (ActivityImpl activityImpl : activityList) {
            if (activityImpl.getActivityBehavior() instanceof UserTaskActivityBehavior) {
                FlowTaskInfoEO flowTaskInfoEO = flowTaskInfoEOService.getFlowTaskInfoEO(processDefinition.getKey(), activityImpl.getId());
                if (flowTaskInfoEO == null) {
                    flowTaskInfoEO = new FlowTaskInfoEO();
                    flowTaskInfoEO.setBindOperationForm("AuditOperation"); // 默认为审核
                    flowTaskInfoEO.setBindType(2);// 默认根据角色配置
                }
                flowTaskInfoEO.setProcessKey(processDefinition.getKey());
                flowTaskInfoEO.setTaskKey(activityImpl.getId());
                flowTaskInfoEO.setTaskName(activityImpl.getProperties().get("name").toString());
                flowTaskInfoEO.setTaskDesc(activityImpl.getProperties().get("name").toString());
                flowTaskInfoEOService.save(flowTaskInfoEO);
                taskKeyList.add(activityImpl.getId());
            }
        }

        // 清除旧流程的步骤
        flowTaskInfoEOService.deleteByTaskKeyNotExist(processDefinition.getKey(), taskKeyList);
    }

}
