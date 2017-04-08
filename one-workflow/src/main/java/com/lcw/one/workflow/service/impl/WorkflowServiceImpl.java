package com.lcw.one.workflow.service.impl;

import com.lcw.one.util.bean.Page;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.service.IWorkflowService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Model;
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

    @Autowired
    private HistoryService historyService;

    @Override
    public WorkFlowBean startWorkflow(WorkFlowBean workFlowBean) {
        String businessKey = workFlowBean.getFlowId() + ":" + workFlowBean.getBusinessKey();
        Map<String, Object> variables = workFlowBean.getVariables();
        variables.put("processBusinessKey", businessKey);
        variables.put("applyUserId", workFlowBean.getUserId());

        ProcessInstance processInstance = null;
        try {
            processInstance = runtimeService.startProcessInstanceByKey(workFlowBean.getFlowId(), businessKey, variables);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException(e.getMessage());
        }
        workFlowBean.setProcessInstanceId(processInstance.getProcessInstanceId());
        workFlowBean.setBusinessKey(processInstance.getBusinessKey());
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
            throw new OneBaseException("查询失败:" + e.getMessage());
        }
        return page;
    }

    @Override
    public WorkFlowBean execWorkflow(WorkFlowBean workFlowBean) {
        try {
            String taskId = workFlowBean.getTaskId();
            String userId = workFlowBean.getUserId();
            Map<String, Object> variables = workFlowBean.getVariables();

            if (StringUtils.isBlank(taskId) || StringUtils.isBlank(userId)) {
                throw new OneBaseException("任务执行失败:taskId||userId为空");
            }

            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

            //先判断是否子流程
            Map<String, Object> instanceVar = runtimeService.getVariables(task.getProcessInstanceId());
            String parentBusinessKey = (String) instanceVar.get("parentBusinessKey");
            workFlowBean.getVariables().put("execUserId", userId);
            //当流程变量中存在父业务KEY时，断定是子流程
            boolean isSubProcess = !StringUtils.isBlank(parentBusinessKey);
            logger.debug(isSubProcess ? "当前为子流程,所属主流程业务编码为：" + parentBusinessKey : "当前为主流程");

            //如果是子流程必须将参数同时放入主流程
            if (isSubProcess && variables != null && !variables.isEmpty()) {
                logger.debug("variables不为空，设置到主流程");
                ProcessInstance parentProcessInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(parentBusinessKey).singleResult();
                runtimeService.setVariables(parentProcessInstance.getId(), variables);
            }
            taskService.setAssignee(taskId, userId);

            taskService.setVariablesLocal(taskId, workFlowBean.getLocalVariables());
            taskService.complete(taskId, variables);
        } catch (Exception e) {
            logger.debug("执行环节任务异常", e);
            throw new OneBaseException(e.getMessage());
        }

        return workFlowBean;
    }

    @Override
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

    @Override
    public Map getTask(String taskId) {
        Map<String, Object> map;
        try {
            TaskQuery taskQuery = taskService.createTaskQuery();
            Task task = taskQuery.taskId(taskId).singleResult();
            map = findTaskInfo(task);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
            throw new OneBaseException("查询失败" + e.getMessage());
        }
        return map;
    }

    @Override
    public InputStream viewProcessImage(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        return inputStream;
    }

    @Override
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
