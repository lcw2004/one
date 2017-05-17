package com.lcw.one.workflow.rest;

import io.swagger.annotations.ApiOperation;

import com.lcw.one.workflow.service.FlowAuditLogEOService;
import com.lcw.one.workflow.entity.FlowAuditLogEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/flow/auditLog")
@Api(description = "审核记录")
public class FlowAuditLogEORestController {

    @Autowired
    private FlowAuditLogEOService flowAuditLogEOService;

    @ApiOperation(value = "审核记录详情")
    @GetMapping("/{id}")
    public ResponseMessage<FlowAuditLogEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(flowAuditLogEOService.get(id));
    }

    @ApiOperation(value = "审核记录列表")
    @GetMapping("")
    public ResponseMessage<List<FlowAuditLogEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(flowAuditLogEOService.findAll());
    }

    @ApiOperation(value = "新增审核记录")
    @PostMapping("")
    public ResponseMessage<FlowAuditLogEO> save(@RequestBody FlowAuditLogEO flowAuditLogEO) {
        return Result.success(flowAuditLogEOService.save(flowAuditLogEO));
        }

    @ApiOperation(value = "修改审核记录")
    @PutMapping("")
    public ResponseMessage<FlowAuditLogEO> update(@RequestBody FlowAuditLogEO flowAuditLogEO) {
        return Result.success(flowAuditLogEOService.update(flowAuditLogEO));
    }

    @ApiOperation(value = "删除审核记录")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        flowAuditLogEOService.delete(id);
        return Result.success();
    }

}
