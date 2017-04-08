package com.lcw.one.workflow.service.impl;

import com.lcw.one.util.bean.Page;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.service.IWorkflowService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkflowServiceImpl implements IWorkflowService {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Override
    public WorkFlowBean startWorkflow(WorkFlowBean workFlowBean) {
        String businessKey = workFlowBean.getFlowId() + ":" + workFlowBean.getBusinessKey();
        Map<String, Object> paramMap = workFlowBean.getParamMap();
        paramMap.put("processBusinessKey", businessKey);
        paramMap.put("applyUserId", workFlowBean.getUserId());

        ProcessInstance processInstance = null;
        try {
//            paramMap.put("bindInfoList_", JSONArray.toJSON(checkProcessRoleDef(processDefinitionKey)));

            processInstance = runtimeService.startProcessInstanceByKey(workFlowBean.getFlowId(), businessKey, paramMap);
        } catch (Exception e) {
            throw new OneBaseException(e.getMessage());
        }

        workFlowBean.getResultMap().put("processInstanceId", processInstance.getProcessInstanceId());
        workFlowBean.getResultMap().put("processBusinessKey", processInstance.getBusinessKey());
        return workFlowBean;
    }

    @Override
    public Page<Map> queryTaskList(Integer pageSize, Integer pageNo, String processInstanceId, String businessKey, String role, String userId, String taskDefinitionKey, String processDefinitionId) {
        Page<Map> page = new Page<>();

        List<Map> list = new ArrayList();
        boolean hasQueryCondition = false;
        TaskQuery taskQuery = taskService.createTaskQuery();
        try {
            if (!StringUtils.isBlank(processInstanceId)) {
                taskQuery = taskQuery.processInstanceId(processInstanceId);
                hasQueryCondition = true;
            }
            if (!StringUtils.isBlank(businessKey)) {
                taskQuery = taskQuery.processInstanceBusinessKey(businessKey);
                hasQueryCondition = true;
            }
            if (!StringUtils.isBlank(role)) {
                taskQuery = taskQuery.taskCandidateGroupIn(Arrays.asList(role.split(",")));
                hasQueryCondition = true;
            }
            if (!StringUtils.isBlank(userId)) {
                taskQuery = taskQuery.taskAssignee(userId);
                hasQueryCondition = true;
            }
            if (!StringUtils.isBlank(taskDefinitionKey)) {
                taskQuery = taskQuery.taskDefinitionKey(taskDefinitionKey);
                hasQueryCondition = true;
            }
            if (!StringUtils.isBlank(processDefinitionId)) {
                taskQuery = taskQuery.processDefinitionId(processDefinitionId);
                hasQueryCondition = true;
            }
            if (!hasQueryCondition) {
                throw new OneBaseException("2", "查询失败:查询条件不能为空，请提供 processInstanceId|businessKey|role|userId");
            }

            List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage((pageNo - 1) * pageSize, pageSize);
            int total = (int) taskQuery.count();

            page.setPageSize(pageSize);
            page.setCount(total);

            for (Task task : taskList) {
                list.add(findTaskInfo(task));
            }

            page.setList(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("2", "查询失败:" + e.getMessage());
        }
        return page;
    }

    @Override
    public void execWorkflow(WorkFlowBean workFlowBean) {

    }


    private Map<String, Object> findTaskInfo(Task task) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taskDefinitionKey", task.getTaskDefinitionKey());
        map.put("formKey", task.getFormKey());
        map.put("processDefinitionId", task.getProcessDefinitionId());
        map.put("processInstanceId", task.getProcessInstanceId());
        map.put("taskName", task.getName());
        map.put("taskId", task.getId());
        map.put("assigneeId", task.getAssignee());
        map.put("taskCreateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(task.getCreateTime()));
        map.put("owner", task.getOwner());
        map.put("isSuspended", task.isSuspended());
        Map<String, Object> vars = runtimeService.getVariables(task.getProcessInstanceId());
        Model model = repositoryService.createModelQuery().modelKey(task.getProcessDefinitionId()).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        map.put("itemsName", processDefinition.getName());
        Map<String, Object> instVars = runtimeService.getVariables(task.getProcessInstanceId());
        String parentBusinessKey = (String) instVars.get("parentBusinessKey");

        if (!StringUtils.isBlank(parentBusinessKey)) {
            //当前为子流程 查找主流程
            List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(parentBusinessKey).list();
            for (ProcessInstance mainProcessInstance : processInstances) {
                logger.debug("suspend main processInstance id " + mainProcessInstance.getId());
                vars.putAll(runtimeService.getVariables(mainProcessInstance.getId()));
            }

        }
        map.put("variables", vars);
        return map;
    }
}
