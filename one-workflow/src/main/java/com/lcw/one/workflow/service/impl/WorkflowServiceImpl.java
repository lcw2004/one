package com.lcw.one.workflow.service.impl;

import com.lcw.one.util.bean.Page;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.service.IWorkflowService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WorkflowServiceImpl implements IWorkflowService {

    @Override
    public void startWorkflow(WorkFlowBean workFlowBean) {

    }

    @Override
    public Page<Map> queryTaskList(String pageSize, String pageNo, String processInstanceId, String businessKey, String role, String userId, String taskDefinitionKey, String processDefinitionId) {

        return null;
    }

    @Override
    public void execWorkflow(WorkFlowBean workFlowBean) {

    }
}
