package com.lcw.one.workflow.rest;

import io.swagger.annotations.ApiOperation;

import com.lcw.one.workflow.service.FlowAuditItemEOService;
import com.lcw.one.workflow.entity.FlowAuditItemEO;
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
@RequestMapping(value = "/${restPath}/flow/auditItem")
@Api(description = "审核事项")
public class FlowAuditItemEORestController {

    @Autowired
    private FlowAuditItemEOService flowAuditItemEOService;

    @ApiOperation(value = "审核事项详情")
    @GetMapping("/{id}")
    public ResponseMessage<FlowAuditItemEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(flowAuditItemEOService.get(id));
    }

    @ApiOperation(value = "审核事项列表")
    @GetMapping("")
    public ResponseMessage<List<FlowAuditItemEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(flowAuditItemEOService.findAll());
    }

    @ApiOperation(value = "新增审核事项")
    @PostMapping("")
    public ResponseMessage<FlowAuditItemEO> save(@RequestBody FlowAuditItemEO flowAuditItemEO) {
        return Result.success(flowAuditItemEOService.save(flowAuditItemEO));
        }

    @ApiOperation(value = "修改审核事项")
    @PutMapping("")
    public ResponseMessage<FlowAuditItemEO> update(@RequestBody FlowAuditItemEO flowAuditItemEO) {
        return Result.success(flowAuditItemEOService.update(flowAuditItemEO));
    }

    @ApiOperation(value = "删除审核事项")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        flowAuditItemEOService.delete(id);
        return Result.success();
    }

}
