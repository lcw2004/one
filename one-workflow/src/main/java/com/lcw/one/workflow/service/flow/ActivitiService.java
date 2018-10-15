package com.lcw.one.workflow.service.flow;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.TaskQueryCondition;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.utils.ThreadLocalContext;
import com.lcw.one.workflow.service.FlowTaskInfoEOService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.io.FilenameUtils;
import com.lcw.one.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

/**
 * 工作流原生接口封装
 */
@Service
public class ActivitiService {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiService.class);

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

    @Autowired
    private ManagementService managementService;

    /**
     * 部署工作流
     *
     * @param is       工作流文件
     * @param fileName 工作流文件名
     * @return
     */
    public List<ProcessDefinition> deploy(InputStream is, String fileName) {
        Deployment deployment;
        String extension = FilenameUtils.getExtension(fileName);
        if (extension.equals("zip") || extension.equals("bar")) {
            ZipInputStream zip = new ZipInputStream(is);
            deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
        } else {
            deployment = repositoryService.createDeployment().addInputStream(fileName, is).deploy();
        }

        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
        if (CollectionUtils.isEmpty(list)) {
            throw new OneBaseException("部署流程失败，请检查是否是有效的流程文件");
        }
        return list;
    }

    /**
     * 通过流程实例ID获取流程图
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public InputStream viewProcessImage(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
    }

    /**
     * 通过流程实例ID获取流程进度图
     *
     * @param processInstanceId 流程实例ID
     * @return
     */
    public InputStream viewProgressImage(String processInstanceId) {
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        ProcessEngineConfiguration processEngineConfig = ((ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine()).getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = processEngineConfig.getProcessDiagramGenerator();

        if (processDefinition != null && processDefinition.isGraphicalNotationDefined()) {
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            return diagramGenerator.generateDiagram(bpmnModel, "png",
                    runtimeService.getActiveActivityIds(processInstance.getId()),
                    Collections.emptyList(),
                    processEngineConfig.getActivityFontName(),
                    processEngineConfig.getLabelFontName(),
                    processEngineConfig.getAnnotationFontName(),
                    processEngineConfig.getClassLoader(),
                    1.0);
        }
        return null;
    }

    /**
     * 启动工作流
     *
     * @param workFlowBean
     * @return
     */
    public WorkFlowBean startWorkflow(WorkFlowBean workFlowBean) {
        // 验证
        if (StringUtils.isEmpty(workFlowBean.getUserId())) {
            throw new OneBaseException("用户ID不能为空");
        }
        if (StringUtils.isEmpty(workFlowBean.getFlowId())) {
            throw new OneBaseException("流程ID不能为空");
        }
        if (StringUtils.isEmpty(workFlowBean.getBusinessKey())) {
            throw new OneBaseException("业务ID不能为空");
        }

        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(workFlowBean.getFlowId()).latestVersion().singleResult();
            if (processDefinition == null) {
                throw new OneBaseException("请先部署工作流");
            }

            String businessKey = workFlowBean.getFlowId() + ":" + workFlowBean.getBusinessKey();
            Map<String, Object> variables = workFlowBean.getVariables();
            variables.put("processBusinessKey", businessKey);
            variables.put("applyUserId", workFlowBean.getUserId());
            variables.put("assigneeId", workFlowBean.getAssigneeId());
            variables.put("processName_", processDefinition.getName());

            identityService.setAuthenticatedUserId(workFlowBean.getUserId());

            // 失败会抛出异常
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workFlowBean.getFlowId(), businessKey, variables);

            workFlowBean.setProcessInstanceId(processInstance.getProcessInstanceId());
            workFlowBean.setBusinessKey(processInstance.getBusinessKey());
            workFlowBean.setTaskDefinitionName(processDefinition.getName());
        } catch (Exception e) {
            return handlerActivitiException(e);
        }
        return workFlowBean;
    }

    /**
     * 执行工作流
     *
     * @param workFlowBean
     * @return
     */
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
            logger.info(isSubProcess ? "当前为子流程,所属主流程业务编码为：" + parentBusinessKey : "当前为主流程");

            //如果是子流程必须将参数同时放入主流程
            Map<String, Object> variables = workFlowBean.getVariables();
            if (isSubProcess && variables != null && !variables.isEmpty()) {
                logger.info("variables不为空，设置到主流程");
                ProcessInstance parentProcessInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(parentBusinessKey).singleResult();
                runtimeService.setVariables(parentProcessInstance.getId(), variables);
            }
            taskService.setAssignee(taskId, userId);

            taskService.setVariablesLocal(taskId, workFlowBean.getLocalVariables());
            taskService.setVariables(taskId, workFlowBean.getVariables());
            taskService.complete(taskId, variables);
        } catch (Exception e) {
            logger.error("执行环节任务异常", e);
            handlerActivitiException(e);
        }

        return workFlowBean;
    }

    /**
     * 查询已办工作流列表
     *
     * @param queryCondition
     * @return
     */
    public PageInfo<TaskInfoBean> queryTaskHistoryList(TaskQueryCondition queryCondition) {
        PageInfo<TaskInfoBean> page = queryCondition.getPageInfo();

        List<TaskInfoBean> list = new ArrayList<>();
        try {
            HistoricTaskInstanceQuery taskQuery = queryCondition.createHistoricTaskInstanceQuery(historyService.createHistoricTaskInstanceQuery());
            List<HistoricTaskInstance> taskList = taskQuery.orderByTaskCreateTime().desc().listPage((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
            page.setCount(taskQuery.count());
            for (HistoricTaskInstance task : taskList) {
                list.add(transToTaskInfoBean(task));
            }
            page.setList(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("查询失败:" + e.getMessage());
        }
        return page;
    }


    /**
     * 查询工作流列表
     *
     * @param queryCondition
     * @return
     */
    public PageInfo<TaskInfoBean> queryTaskList(TaskQueryCondition queryCondition) {
        PageInfo<TaskInfoBean> page = queryCondition.getPageInfo();

        List<TaskInfoBean> list = new ArrayList<>();
        try {
            TaskQuery taskQuery = queryCondition.createTaskQuery(taskService.createTaskQuery());
            List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());

            page.setCount(taskQuery.count());
            for (Task task : taskList) {
                list.add(transToTaskInfoBean(task));
            }
            page.setList(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("查询失败:" + e.getMessage());
        }
        return page;
    }

    /**
     * 查询工作流列表
     *
     * @param queryCondition
     * @return
     */
    public PageInfo<TaskInfoBean> queryTaskListNative(TaskQueryCondition queryCondition) {
        PageInfo<TaskInfoBean> page = queryCondition.getPageInfo();

        List<TaskInfoBean> list = new ArrayList<>();
        try {
            String listSQL = createNativeQuerySQL(queryCondition);
            String countSQL = "select count(1) from (" + listSQL + ") as t";
            logger.info("Native SQL: {}", listSQL);

            NativeTaskQuery listTaskQuery = taskService.createNativeTaskQuery().sql(listSQL);
            buildParams(listTaskQuery, queryCondition);
            List<Task> taskList = listTaskQuery.listPage((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());

            NativeTaskQuery countTaskQuery = taskService.createNativeTaskQuery().sql(countSQL);
            buildParams(countTaskQuery, queryCondition);
            long count = countTaskQuery.count();
            page.setCount(count);

            for (Task task : taskList) {
                list.add(transToTaskInfoBean(task));
            }
            page.setList(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("查询失败:" + e.getMessage());
        }
        return page;
    }

    public NativeTaskQuery buildParams(NativeTaskQuery query, TaskQueryCondition queryCondition) {
        if (queryCondition.getCurrentUser() != null) {
            query.parameter("gourpIn", queryCondition.getCurrentUser().getRoleIds());
            query.parameter("userId", queryCondition.getCurrentUser().getUserId());
        }
        return query;
    }

    public String createNativeQuerySQL(TaskQueryCondition queryCondition) {
        String taskTableName = managementService.getTableName(Task.class);
        String identityLinkTableName = managementService.getTableName(IdentityLinkEntity.class);
        String variableTableName = managementService.getTableName(VariableInstanceEntity.class);

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM ").append(taskTableName).append(" WHERE 1=1 ");

        if (queryCondition.getCurrentUser() != null) {
            sql.append(" AND ID_ IN (");
            sql.append("   SELECT DISTINCT TASK_ID_ FROM ").append(identityLinkTableName);
            sql.append("   WHERE type_ = 'candidate' ");
            sql.append("   AND group_id_ IN (#{gourpIn}) OR user_id_ = #{userId}");
            sql.append(") ");
        }

//        sql.append("AND EXECUTION_ID_ IN (" +
//                "   SELECT DISTINCT execution_id_ FROM act_ru_variable WHERE 1=1 " +
//                "   AND name_ = 'businessName' " +
//                "   AND text_ LIKE '%测试PRO2018022600002%')");

        return sql.toString();
    }

    /**
     * 统计工作流列表
     *
     * @param queryCondition
     * @return
     */
    public long countTaskList(TaskQueryCondition queryCondition) {
        try {
            TaskQuery taskQuery = queryCondition.createTaskQuery(taskService.createTaskQuery());
            return taskQuery.count();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("查询失败:" + e.getMessage());
        }
    }

    /**
     * 删除流程
     *
     * @param processInstanceId
     * @param deleteReason
     */
    public void deleteWorkflowInstance(String processInstanceId, String deleteReason) {
        try {
            long count = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).count();
            if (count > 0) {
                runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new OneBaseException("删除失败");
        }
    }

    /**
     * 查询任务信息
     *
     * @param taskId
     * @return
     */
    public TaskInfoBean getTask(String taskId) {
        TaskInfoBean taskInfoBean = null;
        try {
            TaskQuery taskQuery = taskService.createTaskQuery();
            Task task = taskQuery.taskId(taskId).singleResult();
            if (task == null) {
                throw new OneBaseException("找不到ID" + taskId + "为的任务");
            }

            taskInfoBean = transToTaskInfoBean(task);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new OneBaseException("查询失败" + e.getMessage());
        }
        return taskInfoBean;
    }

    /**
     * 查询历史任务信息
     *
             * @param taskId
     * @return
             */
    public TaskInfoBean getHistoryTask(String taskId) {
        TaskInfoBean taskInfoBean = null;
        try {
            HistoricTaskInstanceQuery taskQuery = historyService.createHistoricTaskInstanceQuery();
            HistoricTaskInstance task = taskQuery.taskId(taskId).singleResult();
            if (task == null) {
                throw new OneBaseException("找不到ID" + taskId + "为的任务");
            }

            taskInfoBean = transToTaskInfoBean(task);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new OneBaseException("查询失败" + e.getMessage());
        }
        return taskInfoBean;
    }

    private TaskInfoBean transToTaskInfoBean(Task task) {
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

        Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
        Map<String, Object> variablesLocal = taskService.getVariablesLocal(task.getId());
        variables.putAll(variablesLocal);
        taskInfoBean.setVariables(variables);
        return taskInfoBean;
    }

    private TaskInfoBean transToTaskInfoBean(HistoricTaskInstance task) {
        TaskInfoBean taskInfoBean = new TaskInfoBean();
        taskInfoBean.setTaskDefinitionKey(task.getTaskDefinitionKey());
        taskInfoBean.setFormKey(task.getFormKey());
        taskInfoBean.setProcessDefinitionId(task.getProcessDefinitionId());
        taskInfoBean.setProcessInstanceId(task.getProcessInstanceId());
        taskInfoBean.setTaskName(task.getName());
        taskInfoBean.setTaskId(task.getId());
        taskInfoBean.setAssigneeId(task.getAssignee());
        taskInfoBean.setTaskCreateTime(task.getCreateTime());
        taskInfoBean.setTaskEndTime(task.getEndTime());
        taskInfoBean.setTaskOwner(task.getOwner());

        HistoricProcessInstance historicInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).includeProcessVariables().singleResult();
        taskInfoBean.setItemsName(historicInstance.getProcessDefinitionName());
        taskInfoBean.setVariables(historicInstance.getProcessVariables());
        return taskInfoBean;
    }

    private WorkFlowBean handlerActivitiException(Exception e) {
        Map<String, String> resultMap = ThreadLocalContext.threadLocal.get();
        if (CollectionUtils.isNotEmpty(resultMap) && resultMap.containsKey("cause")) {
            String cause = resultMap.get("cause");
            ThreadLocalContext.threadLocal.remove();
            throw new OneBaseException(cause);
        } else {
            throw new OneBaseException(e.getMessage(), e);
        }
    }

}
