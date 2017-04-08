package com.lcw.one.workflow.service;

import com.lcw.one.util.bean.Page;
import com.lcw.one.workflow.bean.WorkFlowBean;

import java.util.Map;

public interface IWorkflowService {

    void startWorkflow(WorkFlowBean workFlowBean);


    Page<Map> queryTaskList(String pageSize, String pageNo,
                            String processInstanceId, String businessKey,
                            String role, String userId,
                            String taskDefinitionKey, String processDefinitionId);

    void execWorkflow(WorkFlowBean workFlowBean);

}
