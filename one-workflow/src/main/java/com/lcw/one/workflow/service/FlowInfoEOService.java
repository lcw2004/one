package com.lcw.one.workflow.service;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.workflow.dao.FlowInfoEODao;
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

/**
 * 流程信息相关服务
 */
@Service
public class FlowInfoEOService extends CrudService<FlowInfoEODao, FlowInfoEO, String> {

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FlowTaskInfoEOService flowTaskInfoEOService;

    @Override
    public FlowInfoEO get(String id) {
        FlowInfoEO flowInfoEO = super.get(id);
        flowInfoEO.setFlowTaskInfoEOList(flowTaskInfoEOService.listFlowTaskInfoEOByProcessKey(id));
        return flowInfoEO;
    }

    @Override
    public FlowInfoEO update(FlowInfoEO flowInfoEO) {
        if(CollectionUtils.isNotEmpty(flowInfoEO.getFlowTaskInfoEOList())) {
            flowTaskInfoEOService.save(flowInfoEO.getFlowTaskInfoEOList());
        }
        return flowInfoEO;
    }

    public InputStream progressImage(String processKey) {
        FlowInfoEO flowInfoEO = super.get(processKey);
        if (flowInfoEO == null) {
            throw new OneBaseException("流程" + processKey + "不存在");
        }
        return activitiService.viewProcessImage(flowInfoEO.getProcessDefinitionId());
    }

    public PageInfo<FlowInfoEO> page(PageInfo pageInfo, String likeName) {
        return dao.page(pageInfo, likeName);
    }

    public void deploy(InputStream is, String fileName) {
        List<ProcessDefinition> processDefinitionList = activitiService.deploy(is, fileName);

        for (ProcessDefinition processDefinition : processDefinitionList) {
            FlowInfoEO flowInfoEO = new FlowInfoEO();
            flowInfoEO.setProcessKey(processDefinition.getKey());
            flowInfoEO.setProcessDefinitionId(processDefinition.getId());
            flowInfoEO.setProcessName(processDefinition.getName());
            flowInfoEO.setLastUpdateTime(new Date());
            super.save(flowInfoEO);
            saveFlowTaskInfo(processDefinition);
        }
    }

    private void saveFlowTaskInfo(ProcessDefinition processDefinition) {
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinition.getId());
        List<ActivityImpl> activityList = processDefinitionEntity.getActivities();

        List<String> taskKeyList = new ArrayList<>();
        for (ActivityImpl activityImpl : activityList) {
            if (activityImpl.getActivityBehavior() instanceof UserTaskActivityBehavior) {
                FlowTaskInfoEO flowTaskInfoEO = new FlowTaskInfoEO();
                flowTaskInfoEO.setProcessKey(processDefinition.getKey());
                flowTaskInfoEO.setTaskKey(activityImpl.getId());
                flowTaskInfoEO.setTaskName(activityImpl.getProperties().get("name").toString());
                flowTaskInfoEO.setTaskDesc(activityImpl.getProperties().get("name").toString());
                flowTaskInfoEO.setRankNum(0);
                flowTaskInfoEO.setValid(1);
                flowTaskInfoEO.setX(activityImpl.getX());
                flowTaskInfoEO.setY(activityImpl.getY());
                flowTaskInfoEO.setHeight(activityImpl.getHeight());
                flowTaskInfoEO.setWidth(activityImpl.getWidth());

                flowTaskInfoEOService.save(flowTaskInfoEO);

                taskKeyList.add(activityImpl.getId());
            }
        }

        // 清除旧流程的步骤
        flowTaskInfoEOService.deleteByTaskKeyNotExist(processDefinition.getKey(), taskKeyList);
    }

}
