package com.lcw.one.workflow.rest;

import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.workflow.entity.FlowTaskInfoEO;
import com.lcw.one.workflow.service.FlowTaskInfoEOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/flow/flowTaskInfo")
@Api(description = "流程图")
public class FlowTaskInfoEORestController {

    @Autowired
    private FlowTaskInfoEOService flowTaskInfoEOService;

    @ApiOperation(value = "流程图详情")
    @GetMapping("/{id}")
    public ResponseMessage<FlowTaskInfoEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(flowTaskInfoEOService.get(id));
    }

    @ApiOperation(value = "流程图列表")
    @GetMapping("")
    public ResponseMessage<List<FlowTaskInfoEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(flowTaskInfoEOService.findAll());
    }

    @ApiOperation(value = "新增流程图")
    @PostMapping("")
    public ResponseMessage<FlowTaskInfoEO> save(@RequestBody FlowTaskInfoEO flowTaskInfoEO) {
        return Result.success(flowTaskInfoEOService.save(flowTaskInfoEO));
    }

    @ApiOperation(value = "修改流程图")
    @PutMapping("")
    public ResponseMessage<FlowTaskInfoEO> update(@RequestBody FlowTaskInfoEO flowTaskInfoEO) {
        return Result.success(flowTaskInfoEOService.update(flowTaskInfoEO));
    }

    @ApiOperation(value = "删除流程图")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        flowTaskInfoEOService.delete(id);
        return Result.success();
    }

}
