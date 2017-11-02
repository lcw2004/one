package com.lcw.one.workflow.rest;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.service.flow.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Api(value = "工作流服务", description = "工作流服务接口(仅供测试使用)")
@RestController
public class WorkFlowRestForTestController {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowRestForTestController.class);

    @Autowired
    private ActivitiService activitiService;

    @ApiOperation(value = "启动工作流")
    @PostMapping(value = "/startWorkflow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<WorkFlowBean> startWorkflow(@RequestBody WorkFlowBean workFlowBean) {
        logger.info("startWorkflow[{}]" + GsonUtil.toJson(workFlowBean));
        workFlowBean = activitiService.startWorkflow(workFlowBean);
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

        workFlowBean = activitiService.execWorkflow(workFlowBean);
        return Result.success(workFlowBean);
    }

    @DeleteMapping(value = "/deleteWorkflowInstance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deleteWorkflowInstance(String processInstanceId, String deleteReason) {
        activitiService.deleteWorkflowInstance(processInstanceId, deleteReason);
        return Result.success();
    }

    @ApiOperation(value = "获取任务详情")
    @GetMapping(value = "/getTaskInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<TaskInfoBean> getTaskInfo(String taskId) {
        return Result.success(activitiService.getTask(taskId));
    }

    @ApiOperation(value = "流程进度图片")
    @GetMapping(value = "/progressImage")
    public void progressImage(HttpServletResponse response, String processInstanceId) {
        InputStream is = null;
        byte[] bytes;
        try {
            is = activitiService.viewProgressImage(processInstanceId);
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
