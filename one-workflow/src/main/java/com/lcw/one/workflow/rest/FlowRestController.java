package com.lcw.one.workflow.rest;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.RequestUtils;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.service.FlowService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/flow")
@Api(description = "审核记录")
public class FlowRestController {

    @Autowired
    private FlowService flowService;

    @Autowired
    private WorkFlowRestController workFlowRestController;

    @PostMapping("/startWorkflow")
    public ResponseMessage startWorkflow(String flowId, String businessId, String applyUserId, String applyUserName, @RequestBody Map<String, Object> variables) {
        flowService.startWorkflow(flowId, businessId, applyUserId, applyUserName, variables);
        return Result.success();
    }

    @GetMapping("/audit")
    public ResponseMessage audit(String taskId, String auditUserId, Boolean auditResult, String remark) {
        if(StringUtils.isEmpty(taskId)) {
            throw new OneBaseException("任务ID不能为空");
        }
        if(StringUtils.isEmpty(auditUserId)) {
            throw new OneBaseException("审核人ID不能为空");
        }
        if (!auditResult && StringUtils.isEmpty(remark)) {
            throw new OneBaseException("请输入驳回原因");
        }

        flowService.audit(taskId, auditUserId, auditResult, remark);
        return Result.success();
    }

    @GetMapping(value = "/task/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<PageInfo<TaskInfoBean>> queryTaskList(HttpServletRequest request, Integer pageSize, Integer pageNo,
                                                                 String processInstanceId, String businessKey,
                                                                 String taskDefinitionKey, String processDefinitionKey) {
        String userId = null;
        String roleIds = RequestUtils.getLoginRoleId(request);
        PageInfo<TaskInfoBean> page = workFlowRestController.queryTaskList(pageSize, pageNo, roleIds, userId, processInstanceId, businessKey, taskDefinitionKey, processDefinitionKey).getData();
        return Result.success(page);
    }
}
