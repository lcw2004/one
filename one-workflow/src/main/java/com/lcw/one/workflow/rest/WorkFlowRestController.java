package com.lcw.one.workflow.rest;

import com.alibaba.fastjson.JSON;
import com.lcw.one.util.bean.Page;
import com.lcw.one.util.bean.ResponseMessage;
import com.lcw.one.util.bean.Result;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.service.IWorkflowService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
public class WorkFlowRestController {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowRestController.class);

    @Autowired
    private IWorkflowService iWorkflowService;

    /**
     * 启动流程
     */
    @PostMapping(value = "/startWorkflow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage startWorkflow(@RequestBody WorkFlowBean workFlowBean) {
        logger.info("startWorkflow[{}]" + JSON.toJSONString(workFlowBean));
        workFlowBean = iWorkflowService.startWorkflow(workFlowBean);
        return Result.success(workFlowBean);
    }

    /**
     * 执行流程
     */
    @PostMapping(value = "/execWorkflow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage execWorkflow(@RequestBody WorkFlowBean workFlowBean) {
        logger.info("startWorkflow[{}]" + JSON.toJSONString(workFlowBean));
        workFlowBean = iWorkflowService.execWorkflow(workFlowBean);
        return Result.success(workFlowBean);
    }

    /**
     * 查询任务列表
     */
    @GetMapping(value = "/queryTaskList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<Page<Map>> queryTaskList(Integer pageSize, Integer pageNo,
                                                    String processInstanceId, String businessKey,
                                                    String role, String userId,
                                                    String taskDefinitionKey, String processDefinitionId) {
        Page<Map> page = iWorkflowService.queryTaskList(pageSize, pageNo, processInstanceId, businessKey, role, userId, taskDefinitionKey, processDefinitionId);
        return Result.success(page);
    }

    @GetMapping(value = "/getTaskInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<Map> getTaskInfo(String taskId) {
        return Result.success(iWorkflowService.getTask(taskId));
    }

    @RequestMapping(value = "/processImage")
    public void processImage(HttpServletResponse response, @RequestParam(value = "processDefinitionId") String processDefinitionId) {
        InputStream is = null;
        byte[] bytes;
        try {
            is = iWorkflowService.viewProcessImage(processDefinitionId);
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

    @RequestMapping(value = "/progressImage")
    public void progressImage(HttpServletResponse response, @RequestParam(value = "processInstanceId") String processInstanceId) {
        InputStream is = null;
        byte[] bytes;
        try {
            is = iWorkflowService.viewProgressImage(processInstanceId);
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
