package com.lcw.one.workflow.service;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.workflow.dao.FlowStateEODao;
import com.lcw.one.workflow.entity.FlowStateEO;
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
public class FlowStateEOService extends CrudService<FlowStateEODao, FlowStateEO> {

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FlowTaskInfoEOService flowTaskInfoEOService;

    @Override
    public FlowStateEO get(String id) {
        FlowStateEO flowStateEO = super.get(id);
        flowStateEO.setFlowTaskInfoEOList(flowTaskInfoEOService.listFlowTaskInfoEOByProcessKey(id));
        return flowStateEO;
    }

    @Override
    public FlowStateEO update(FlowStateEO flowStateEO) {
        if(CollectionUtils.isNotEmpty(flowStateEO.getFlowTaskInfoEOList())) {
            flowTaskInfoEOService.save(flowStateEO.getFlowTaskInfoEOList());
        }
        return flowStateEO;
    }

    public InputStream progressImage(String processKey) {
        FlowStateEO flowStateEO = super.get(processKey);
        if (flowStateEO == null) {
            throw new OneBaseException("流程" + processKey + "不存在");
        }
        return activitiService.viewProcessImage(flowStateEO.getProcessDefinitionId());
    }

    public PageInfo<FlowStateEO> page(PageInfo pageInfo, String likeName) {
        return dao.page(pageInfo, likeName);
    }

    public void deploy(InputStream is, String fileName) {
        List<ProcessDefinition> processDefinitionList = activitiService.deploy(is, fileName);

        for (ProcessDefinition processDefinition : processDefinitionList) {
            FlowStateEO flowStateEO = new FlowStateEO();
            flowStateEO.setProcessKey(processDefinition.getKey());
            flowStateEO.setProcessDefinitionId(processDefinition.getId());
            flowStateEO.setProcessName(processDefinition.getName());
            flowStateEO.setLastUpdateTime(new Date());
            super.save(flowStateEO);
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
