package com.lcw.one.workflow.service;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.entity.FlowTaskInfoEO;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class WorkflowService {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowService.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowTaskInfoEOService processTaskInfoEOService;

    public WorkFlowBean startWorkflow(WorkFlowBean workFlowBean) {
        String businessKey = workFlowBean.getFlowId() + ":" + workFlowBean.getBusinessKey();
        Map<String, Object> variables = workFlowBean.getVariables();
        variables.put("processBusinessKey", businessKey);
        variables.put("applyUserId", workFlowBean.getUserId());

        // 获取绑定的表单信息以及角色信息
        List<FlowTaskInfoEO> taskInfoEOList = processTaskInfoEOService.listFlowTaskInfoEOByProcessKeyAndValid(workFlowBean.getFlowId());
        variables.put("bindInfoList_", GsonUtil.toJson(taskInfoEOList));

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(workFlowBean.getFlowId()).latestVersion().singleResult();
        variables.put("processName_", processDefinition.getName());

        ProcessInstance processInstance = null;
        try {
            identityService.setAuthenticatedUserId(workFlowBean.getUserId());
            processInstance = runtimeService.startProcessInstanceByKey(workFlowBean.getFlowId(), businessKey, variables);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException(e.getMessage());
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

        workFlowBean.setProcessInstanceId(processInstance.getProcessInstanceId());
        workFlowBean.setBusinessKey(processInstance.getBusinessKey());
        workFlowBean.setTaskDefinitionName(processDefinition.getName());
        return workFlowBean;
    }

    public PageInfo<TaskInfoBean> queryTaskList(Integer pageSize, Integer pageNo, String roleIds, String userId, String processInstanceId, String businessKey, String taskDefinitionKey, String processDefinitionKey) {
        PageInfo<TaskInfoBean> page = new PageInfo<>();

        List<TaskInfoBean> list = new ArrayList<>();
        TaskQuery taskQuery = taskService.createTaskQuery();
        try {
            // 角色ID
            if (StringUtils.isNotEmpty(roleIds)) {
                taskQuery = taskQuery.taskCandidateGroupIn(Arrays.asList(roleIds.split(",")));
            }
            // 用户ID
            if (StringUtils.isNotEmpty(userId)) {
                taskQuery = taskQuery.taskAssignee(userId);
            }
            // 流程实例ID
            if (StringUtils.isNotEmpty(processInstanceId)) {
                taskQuery = taskQuery.processInstanceId(processInstanceId);
            }
            // 业务参数ID
            if (StringUtils.isNotEmpty(businessKey)) {
                taskQuery = taskQuery.processInstanceBusinessKey(businessKey);
            }
            // 流程节点ID
            if (StringUtils.isNotEmpty(taskDefinitionKey)) {
                taskQuery = taskQuery.taskDefinitionKey(taskDefinitionKey);
            }
            // 流程ID
            if (StringUtils.isNotEmpty(processDefinitionKey)) {
                taskQuery = taskQuery.processDefinitionKey(processDefinitionKey);
            }

            List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage((pageNo - 1) * pageSize, pageSize);
            page.setPageSize(pageSize);
            page.setCount(taskQuery.count());
            for (Task task : taskList) {
                list.add(findTaskInfo(task));
            }
            page.setList(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("查询失败:" + e.getMessage());
        }
        return page;
    }


    public PageInfo<TaskInfoBean> queryTaskListNative(Integer pageSize, Integer pageNo, String roleIds, String userId, String processInstanceId, String businessKey, String taskDefinitionKey, String processDefinitionKey) {
        PageInfo<TaskInfoBean> page = new PageInfo<>();

        List<TaskInfoBean> list = new ArrayList<>();
        TaskQuery taskQuery = taskService.createTaskQuery();
        try {
            // 角色ID
            if (StringUtils.isNotEmpty(roleIds)) {
                taskQuery = taskQuery.taskCandidateGroupIn(Arrays.asList(roleIds.split(",")));
            }
            // 用户ID
            if (StringUtils.isNotEmpty(userId)) {
                taskQuery = taskQuery.taskAssignee(userId);
            }


            // 流程实例ID
            if (StringUtils.isNotEmpty(processInstanceId)) {
                taskQuery = taskQuery.processInstanceId(processInstanceId);
            }
            // 业务参数ID
            if (StringUtils.isNotEmpty(businessKey)) {
                taskQuery = taskQuery.processInstanceBusinessKey(businessKey);
            }
            // 流程节点ID
            if (StringUtils.isNotEmpty(taskDefinitionKey)) {
                taskQuery = taskQuery.taskDefinitionKey(taskDefinitionKey);
            }
            // 流程ID
            if (StringUtils.isNotEmpty(processDefinitionKey)) {
                taskQuery = taskQuery.processDefinitionKey(processDefinitionKey);
            }

            List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage((pageNo - 1) * pageSize, pageSize);
            page.setPageSize(pageSize);
            page.setCount(taskQuery.count());
            for (Task task : taskList) {
                list.add(findTaskInfo(task));
            }
            page.setList(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("查询失败:" + e.getMessage());
        }
        return page;
    }


    public WorkFlowBean execWorkflow(WorkFlowBean workFlowBean) {
        try {
            String taskId = workFlowBean.getTaskId();
            String userId = workFlowBean.getUserId();
            if (StringUtils.isBlank(taskId) || StringUtils.isBlank(userId)) {
                throw new OneBaseException("任务执行失败:taskId||userId为空");
            }

            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if (task == null) {
                throw new OneBaseException("找不到ID" + taskId + "为的任务");
            }

            //先判断是否子流程
            Map<String, Object> instanceVar = runtimeService.getVariables(task.getProcessInstanceId());
            String parentBusinessKey = (String) instanceVar.get("parentBusinessKey");
            workFlowBean.getVariables().put("execUserId", userId);
            //当流程变量中存在父业务KEY时，断定是子流程
            boolean isSubProcess = !StringUtils.isBlank(parentBusinessKey);
            logger.debug(isSubProcess ? "当前为子流程,所属主流程业务编码为：" + parentBusinessKey : "当前为主流程");

            //如果是子流程必须将参数同时放入主流程
            Map<String, Object> variables = workFlowBean.getVariables();
            if (isSubProcess && variables != null && !variables.isEmpty()) {
                logger.debug("variables不为空，设置到主流程");
                ProcessInstance parentProcessInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(parentBusinessKey).singleResult();
                runtimeService.setVariables(parentProcessInstance.getId(), variables);
            }
            taskService.setAssignee(taskId, userId);

            taskService.setVariablesLocal(taskId, workFlowBean.getLocalVariables());
            taskService.complete(taskId, variables);
        } catch (Exception e) {
            logger.error("执行环节任务异常", e);
            throw new OneBaseException(e.getMessage());
        }

        return workFlowBean;
    }

    public void deleteWorkflowInstance(String processInstanceId, String deleteReason) {
        try {
            long count = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).count();
            if (count > 0) {
                runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
            throw new OneBaseException("删除失败");
        }
    }

    public TaskInfoBean getTask(String taskId) {
        TaskInfoBean taskInfoBean = null;
        try {
            TaskQuery taskQuery = taskService.createTaskQuery();
            Task task = taskQuery.taskId(taskId).singleResult();
            if (task == null) {
                throw new OneBaseException("找不到ID" + taskId + "为的任务");
            }

            taskInfoBean = findTaskInfo(task);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
            throw new OneBaseException("查询失败" + e.getMessage());
        }
        return taskInfoBean;
    }

    public InputStream viewProcessImage(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        return inputStream;
    }

    public InputStream viewProgressImage(String processInstanceId) {
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        ProcessEngineConfiguration processEngineConfig = ((ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine()).getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = processEngineConfig.getProcessDiagramGenerator();
        logger.info("ActivityFontName:songti ? " + processEngineConfig.getActivityFontName().equals("宋体"));

        if (processDefinition != null && processDefinition.isGraphicalNotationDefined()) {
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            InputStream definitionImageStream = diagramGenerator.generateDiagram(bpmnModel, "png",
                    runtimeService.getActiveActivityIds(processInstance.getId()), Collections.<String>emptyList(),
                    processEngineConfig.getActivityFontName(), processEngineConfig.getLabelFontName(), processEngineConfig.getAnnotationFontName(),
                    processEngineConfig.getClassLoader(), 1.0);
            return definitionImageStream;
        }
        return null;
    }

    private TaskInfoBean findTaskInfo(Task task) {
        TaskInfoBean taskInfoBean = new TaskInfoBean();
        taskInfoBean.setTaskDefinitionKey(task.getTaskDefinitionKey());
        taskInfoBean.setFormKey(task.getFormKey());
        taskInfoBean.setProcessDefinitionId(task.getProcessDefinitionId());
        taskInfoBean.setProcessInstanceId(task.getProcessInstanceId());
        taskInfoBean.setTaskName(task.getName());
        taskInfoBean.setTaskId(task.getId());
        taskInfoBean.setAssigneeId(task.getAssignee());
        taskInfoBean.setTaskCreateTime(task.getCreateTime());
        taskInfoBean.setTaskOwner(task.getOwner());
        taskInfoBean.setIsSuspended(task.isSuspended());

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        taskInfoBean.setItemsName(processDefinition.getName());

        Map<String, Object> instVars = runtimeService.getVariables(task.getProcessInstanceId());
        Map<String, Object> vars = runtimeService.getVariables(task.getProcessInstanceId());
        vars.putAll(instVars);
        taskInfoBean.setVariables(vars);
        return taskInfoBean;
    }
}
