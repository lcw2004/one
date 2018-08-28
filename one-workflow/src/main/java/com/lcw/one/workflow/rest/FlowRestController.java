package com.lcw.one.workflow.rest;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.util.annotation.RequireUser;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.ImageUtils;
import com.lcw.one.util.utils.http.HttpUtils;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.TaskQueryCondition;
import com.lcw.one.workflow.bean.constant.FlowQueryTypeEnum;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
import com.lcw.one.workflow.service.FlowAuditItemEOService;
import com.lcw.one.workflow.service.WorkFlowService;
import com.lcw.one.workflow.service.flow.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping(value = "/${restPath}/flow")
@Api(description = "审核记录")
public class FlowRestController {

    private static final Logger logger = LoggerFactory.getLogger(FlowRestController.class);

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private FlowAuditItemEOService flowAuditItemEOService;

    @RequireUser
    @ApiOperation(value = "待办任务")
    @GetMapping(value = "/task/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<PageInfo<TaskInfoBean>> queryTaskList(HttpServletRequest request, @ModelAttribute TaskQueryCondition queryCondition) {
        queryCondition.setCurrentUser(LoginUserUtils.getCurrentUser(request));
        PageInfo<TaskInfoBean> page = activitiService.queryTaskList(queryCondition);
        return Result.success(page);
    }

    @ApiOperation(value = "任务详情")
    @GetMapping(value = "/task/todo/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<TaskInfoBean> getTaskInfo(@PathVariable String taskId) {
        TaskInfoBean taskInfoBean = activitiService.getTask(taskId);
        return Result.success(taskInfoBean);
    }

    @ApiOperation(value = "历史任务详情")
    @GetMapping(value = "/task/history/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<TaskInfoBean> getHistoryTask(@PathVariable String taskId) {
        TaskInfoBean taskInfoBean = activitiService.getHistoryTask(taskId);
        return Result.success(taskInfoBean);
    }

    @RequireUser
    @ApiOperation(value = "已办任务")
    @GetMapping(value = "/task/done", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<PageInfo<TaskInfoBean>> queryTaskHistoryList(HttpServletRequest request, @ModelAttribute TaskQueryCondition queryCondition) {
        LoginUser loginUser = LoginUserUtils.getCurrentUser(request);
        if (queryCondition.getQueryType() == FlowQueryTypeEnum.BY_ASSIGNEE_ID.getValue()) {
            queryCondition.setAssigneeId(loginUser.getUserId());
        } else if (queryCondition.getQueryType() == FlowQueryTypeEnum.BY_APPLY_USER_ID.getValue()) {
            queryCondition.setApplyUserId(loginUser.getUserId());
        } else {
            queryCondition.setAssigneeId(loginUser.getUserId());
        }
        // 只显示已结束的任务
        queryCondition.setWorkflowStatus("1");
        PageInfo<TaskInfoBean> page = activitiService.queryTaskHistoryList(queryCondition);
        return Result.success(page);
    }

    @ApiOperation(value = "流程进度图")
    @GetMapping(value = "/progressImage/{businessId}/{businessType}")
    public void processImage(HttpServletResponse response, @PathVariable String businessId, @PathVariable String businessType) {
        InputStream is = null;
        try {
            FlowAuditItemEO flowAuditItemEO = flowAuditItemEOService.getByBusinessIdAndNotFinished(businessId, businessType);
            if (flowAuditItemEO == null) {
                throw new OneBaseException("没找到对应的流程数据");
            }
            is = activitiService.viewProgressImage(flowAuditItemEO.getFlowInstanceId());
            byte[] bytes = ImageUtils.cutImage(is, 10);
            HttpUtils.addImageHeader(response);
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    @ApiOperation(value = "审核")
    @GetMapping("/audit")
    public ResponseMessage audit(HttpServletRequest request,
                                 @NotBlank(message = "任务ID不能为空") String taskId,
                                 @NotBlank(message = "审核结果不能为空") Boolean auditResult,
                                 String remark) {
        workFlowService.audit(LoginUserUtils.getCurrentUser(request), taskId, auditResult, remark, null);
        return Result.success();
    }

    @ApiOperation(value = "流程进度图")
    @GetMapping(value = "/progressImage/{taskId}")
    public void processImageByTaskId(HttpServletResponse response, @PathVariable String taskId) {
        InputStream is = null;
        try {
            TaskInfoBean taskInfoBean = activitiService.getTask(taskId);
            if (taskInfoBean == null) {
                throw new OneBaseException("没找到对应的流程数据");
            }
            // 查看流程图
            is = activitiService.viewProgressImage(taskInfoBean.getProcessInstanceId());

            // 添加Header
            HttpUtils.addCacheImageHeader(response);

            // 剪切并返回图片
            ImageUtils.cutImage(is, response.getOutputStream(), 10);
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }


}
