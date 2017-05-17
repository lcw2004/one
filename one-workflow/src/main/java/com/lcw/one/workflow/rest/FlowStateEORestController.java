package com.lcw.one.workflow.rest;

import com.lcw.one.workflow.entity.FlowStateEO;
import io.swagger.annotations.ApiOperation;

import com.lcw.one.workflow.service.FlowStateEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/flow/flowState")
@Api(description = "流程图状态")
public class FlowStateEORestController {

    @Autowired
    private FlowStateEOService flowStateEOService;

    @ApiOperation(value = "流程图状态详情")
    @GetMapping("/{id}")
    public ResponseMessage<FlowStateEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(flowStateEOService.get(id));
    }

    @ApiOperation(value = "流程图状态列表")
    @GetMapping("")
    public ResponseMessage<List<FlowStateEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(flowStateEOService.findAll());
    }

    @ApiOperation(value = "新增流程图状态")
    @PostMapping("")
    public ResponseMessage<FlowStateEO> save(@RequestBody FlowStateEO flowStateEO) {
        return Result.success(flowStateEOService.save(flowStateEO));
        }

    @ApiOperation(value = "修改流程图状态")
    @PutMapping("")
    public ResponseMessage<FlowStateEO> update(@RequestBody FlowStateEO flowStateEO) {
        return Result.success(flowStateEOService.update(flowStateEO));
    }

    @ApiOperation(value = "删除流程图状态")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        flowStateEOService.delete(id);
        return Result.success();
    }

}
