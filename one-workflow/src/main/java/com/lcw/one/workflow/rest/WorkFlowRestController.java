package com.lcw.one.workflow.rest;

import com.alibaba.fastjson.JSON;
import com.lcw.one.util.bean.Page;
import com.lcw.one.util.bean.ResponseMessage;
import com.lcw.one.util.bean.Result;
import com.lcw.one.workflow.bean.WorkFlowBean;
import com.lcw.one.workflow.service.IWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
