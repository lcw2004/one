package com.lcw.one.workflow.service;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.StringUtils;
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
    private FlowTaskInfoEOService flowTaskInfoEOService;

    @Override
    public FlowInfoEO get(String id) {
        FlowInfoEO flowInfoEO = super.get(id);
        if (flowInfoEO != null) {
            flowInfoEO.setFlowTaskList(flowTaskInfoEOService.listFlowTaskInfoEOByProcessKey(id));
        }
        return flowInfoEO;
    }

    public FlowInfoEO getAndValid(String id) {
        FlowInfoEO flowInfoEO = super.get(id);

        if (flowInfoEO == null) {
            throw new OneBaseException("流程[" + id + "]未配置表单");
        }
        if (StringUtils.isEmpty(flowInfoEO.getBindViewForm())) {
            throw new OneBaseException("流程[" + flowInfoEO.getProcessName() + "]未配置表单");
        }
        return flowInfoEO;
    }

    @Override
    public FlowInfoEO update(FlowInfoEO flowInfoEO) {
        super.update(flowInfoEO);
        if(CollectionUtils.isNotEmpty(flowInfoEO.getFlowTaskList())) {
            flowTaskInfoEOService.save(flowInfoEO.getFlowTaskList());
        }
        return flowInfoEO;
    }

    @Override
    public void delete(String s) {
        flowTaskInfoEOService.deleteByProcessKey(s);
        super.delete(s);
    }

    public PageInfo<FlowInfoEO> page(PageInfo pageInfo, String likeName) {
        return dao.page(pageInfo, likeName);
    }

}
