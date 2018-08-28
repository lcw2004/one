package com.lcw.one.workflow.rest;

import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.workflow.entity.FlowAuditItemDetailEO;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import com.lcw.one.workflow.service.FlowAuditItemDetailEOService;
import com.lcw.one.workflow.service.FlowAuditLogEOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/flow/audit")
@Api(description = "审核记录")
public class FlowAuditItemRestController {

    @Autowired
    private FlowAuditLogEOService flowAuditLogEOService;

    @Autowired
    private FlowAuditItemDetailEOService flowAuditItemDetailEOService;

    @ApiOperation(value = "审核记录列表")
    @GetMapping("/log")
    public ResponseMessage<List<FlowAuditLogEO>> listLog(String businessType, String businessId, String secondBusinessId) {
        return Result.success(flowAuditLogEOService.list(businessType, businessId, secondBusinessId));
    }

    @ApiOperation(value = "审核记录列表")
    @GetMapping("/{auditItemId}/log")
    public ResponseMessage<List<FlowAuditLogEO>> listLog(@PathVariable String auditItemId) {
        return Result.success(flowAuditLogEOService.list(auditItemId));
    }

    @ApiOperation(value = "审核变更详情列表")
    @GetMapping("/{auditItemId}/detail")
    public ResponseMessage<List<FlowAuditItemDetailEO>> listDetail(@PathVariable String auditItemId) {
        return Result.success(flowAuditItemDetailEOService.listByAuditItemId(auditItemId));
    }

}
