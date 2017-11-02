package com.lcw.one.workflow.rest;

import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import com.lcw.one.workflow.service.FlowAuditLogEOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/flow/auditLog")
@Api(description = "审核记录")
public class FlowAuditLogEORestController {

    @Autowired
    private FlowAuditLogEOService flowAuditLogEOService;

    @ApiOperation(value = "审核记录列表")
    @GetMapping("")
    public ResponseMessage<List<FlowAuditLogEO>> list(String businessType, String businessId, String secondBusinessId) {
        return Result.success(flowAuditLogEOService.list(businessType, businessId, secondBusinessId));
    }

}
