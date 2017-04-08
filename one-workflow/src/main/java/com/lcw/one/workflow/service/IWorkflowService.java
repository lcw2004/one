package com.lcw.one.workflow.service;

import com.lcw.one.util.bean.Page;
import com.lcw.one.workflow.bean.WorkFlowBean;

import java.io.InputStream;
import java.util.Map;

public interface IWorkflowService {

    WorkFlowBean startWorkflow(WorkFlowBean workFlowBean);

    WorkFlowBean execWorkflow(WorkFlowBean workFlowBean);

    Page<Map> queryTaskList(Integer pageSize, Integer pageNo,
                            String processInstanceId, String businessKey,
                            String role, String userId,
                            String taskDefinitionKey, String processDefinitionId);

    Map getTask(String taskId);

    void deleteWorkflowInstance(String processInstanceId, String deleteReason);

    InputStream viewProcessImage(String processDefinitionId);

    InputStream viewProgressImage(String processInstanceId);
}
