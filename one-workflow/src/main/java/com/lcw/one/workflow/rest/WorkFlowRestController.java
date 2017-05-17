package com.lcw.one.workflow.rest;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.service.WorkflowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Api(value = "工作流服务", description = "工作流服务接口")
@RestController
public class WorkFlowRestController {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowRestController.class);

    @Autowired
    private WorkflowService workflowService;

    @ApiOperation(value = "启动工作流")
    @PostMapping(value = "/startWorkflow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<WorkFlowBean> startWorkflow(@RequestBody WorkFlowBean workFlowBean) {
        logger.info("startWorkflow[{}]" + GsonUtil.toJson(workFlowBean));

        // 验证
        if (StringUtils.isEmpty(workFlowBean.getUserId())) {
            return Result.error("用户ID不能为空");
        }
        if (StringUtils.isEmpty(workFlowBean.getFlowId())) {
            return Result.error("流程ID不能为空");
        }
        if (StringUtils.isEmpty(workFlowBean.getBusinessKey())) {
            return Result.error("业务ID不能为空");
        }

        workFlowBean = workflowService.startWorkflow(workFlowBean);
        return Result.success(workFlowBean);
    }

    @ApiOperation(value = "执行工作流")
    @PostMapping(value = "/execWorkflow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<WorkFlowBean> execWorkflow(@Valid @RequestBody WorkFlowBean workFlowBean) {
        logger.info("startWorkflow[{}]" + GsonUtil.toJson(workFlowBean));

        // 验证
        if (StringUtils.isEmpty(workFlowBean.getUserId())) {
            return Result.error("用户ID不能为空");
        }
        if (StringUtils.isEmpty(workFlowBean.getTaskId())) {
            return Result.error("任务ID不能为空");
        }

        workFlowBean = workflowService.execWorkflow(workFlowBean);
        return Result.success(workFlowBean);
    }

    @DeleteMapping(value = "/deleteWorkflowInstance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deleteWorkflowInstance(String processInstanceId, String deleteReason) {
        workflowService.deleteWorkflowInstance(processInstanceId, deleteReason);
        return Result.success();
    }

    @ApiOperation(value = "查询任务列表")
    @GetMapping(value = "/queryTaskList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<PageInfo<TaskInfoBean>> queryTaskList(Integer pageSize, Integer pageNo, String roleIds, String userId, String processInstanceId, String businessKey, String taskDefinitionKey, String processDefinitionKey) {
        if (StringUtils.isEmpty(processInstanceId) && StringUtils.isEmpty(businessKey) && StringUtils.isEmpty(roleIds) &&
                StringUtils.isEmpty(userId) && StringUtils.isEmpty(taskDefinitionKey) && StringUtils.isEmpty(processDefinitionKey)) {
            return Result.error("请输入至少一个查询条件");
        }

        PageInfo<TaskInfoBean> page = workflowService.queryTaskList(pageSize, pageNo, roleIds, userId, processInstanceId, businessKey, taskDefinitionKey, processDefinitionKey);
        return Result.success(page);
    }

    @ApiOperation(value = "查询任务列表")
    @GetMapping(value = "/queryTaskListNative", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<PageInfo<TaskInfoBean>> queryTaskListNative(Integer pageSize, Integer pageNo, String roleIds, String userId, String processInstanceId, String businessKey, String taskDefinitionKey, String processDefinitionKey) {
        if (StringUtils.isEmpty(processInstanceId) && StringUtils.isEmpty(businessKey) && StringUtils.isEmpty(roleIds) &&
                StringUtils.isEmpty(userId) && StringUtils.isEmpty(taskDefinitionKey) && StringUtils.isEmpty(processDefinitionKey)) {
            return Result.error("请输入至少一个查询条件");
        }

        PageInfo<TaskInfoBean> page = workflowService.queryTaskListNative(pageSize, pageNo, roleIds, userId, processInstanceId, businessKey, taskDefinitionKey, processDefinitionKey);
        return Result.success(page);
    }

    @ApiOperation(value = "获取任务详情")
    @GetMapping(value = "/getTaskInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<TaskInfoBean> getTaskInfo(String taskId) {
        return Result.success(workflowService.getTask(taskId));
    }

    @ApiOperation(value = "流程图图片")
    @GetMapping(value = "/processImage")
    public void processImage(HttpServletResponse response, String processDefinitionId) {
        InputStream is = null;
        byte[] bytes;
        try {
            is = workflowService.viewProcessImage(processDefinitionId);
            bytes = IOUtils.toByteArray(is);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    @ApiOperation(value = "流程进度图片")
    @GetMapping(value = "/progressImage")
    public void progressImage(HttpServletResponse response, String processInstanceId) {
        InputStream is = null;
        byte[] bytes;
        try {
            is = workflowService.viewProgressImage(processInstanceId);
            bytes = IOUtils.toByteArray(is);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

}
