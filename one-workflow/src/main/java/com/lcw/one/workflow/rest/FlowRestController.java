package com.lcw.one.workflow.rest;

import com.lcw.one.util.annotation.RequireUser;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.ImageUtils;
import com.lcw.one.util.utils.LoginUserUtils;
import com.lcw.one.util.utils.RequestUtils;
import com.lcw.one.workflow.bean.TaskInfoBean;
import com.lcw.one.workflow.bean.TaskQueryCondition;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
import com.lcw.one.workflow.service.FlowAuditItemEOService;
import com.lcw.one.workflow.service.WorkFlowService;
import com.lcw.one.workflow.service.flow.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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

    /**
     * 启动工作流
     *
     * @param flowId        流程定义ID
     * @param businessId    业务ID
     * @param businessName  业务对象名称
     * @param applyUserId   申请人ID
     * @param applyUserName 申请人名称
     * @param variables     其他参数
     * @return
     */
    @PostMapping("/startWorkflow")
    public ResponseMessage startWorkflow(String flowId, String businessId, String secondBusinessId, String businessName, String applyUserId, String applyUserName, String operateName, String ip, @RequestBody Map<String, Object> variables) {
        workFlowService.startWorkflow(flowId, businessId, secondBusinessId, businessName, applyUserId, applyUserName, operateName, ip, variables);
        return Result.success();
    }

    @GetMapping("/audit")
    public ResponseMessage audit(HttpServletRequest request, String taskId, String auditUserId, Boolean auditResult, String remark) {
        String userId = LoginUserUtils.getLoginUserId(request);
        String ip = RequestUtils.getClientIp(request);
        if (StringUtils.isEmpty(taskId)) {
            throw new OneBaseException("任务ID不能为空");
        }
        if (StringUtils.isEmpty(auditUserId)) {
            throw new OneBaseException("审核人ID不能为空");
        }
        if (!auditResult && StringUtils.isEmpty(remark)) {
            throw new OneBaseException("请输入驳回原因");
        }

        workFlowService.audit(taskId, userId, auditResult, remark, ip);
        return Result.success();
    }

    @RequireUser
    @GetMapping(value = "/task/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<PageInfo<TaskInfoBean>> queryTaskList(HttpServletRequest request, @ModelAttribute TaskQueryCondition queryCondition) {
        queryCondition.setUserId(null);
        queryCondition.setRoleIds(LoginUserUtils.getLoginRoleId(request));
        PageInfo<TaskInfoBean> page = activitiService.queryTaskList(queryCondition);
        return Result.success(page);
    }

    @GetMapping(value = "/task/todo/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<TaskInfoBean> getTaskInfo(@PathVariable("taskId") String taskId) {
        TaskInfoBean taskInfoBean = activitiService.getTask(taskId);
        return Result.success(taskInfoBean);
    }

    @ApiOperation(value = "流程进度图")
    @GetMapping(value = "/progressImage/{businessId}/{businessType}")
    public void processImage(HttpServletResponse response, @PathVariable("businessId") String businessId, @PathVariable("businessType") String businessType) {
        InputStream is = null;

        try {
            FlowAuditItemEO flowAuditItemEO = flowAuditItemEOService.getByBusinessIdAndNotFinished(businessId, businessType);
            if (flowAuditItemEO == null) {
                throw new OneBaseException("没找到对应的流程数据");
            }
            is = activitiService.viewProgressImage(flowAuditItemEO.getFlowInstanceId());
            byte[] bytes = ImageUtils.cutImage(is, 10);

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
