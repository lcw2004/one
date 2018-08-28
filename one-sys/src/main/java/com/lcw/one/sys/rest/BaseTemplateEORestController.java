package com.lcw.one.sys.rest;

import com.lcw.one.baseInfo.entity.BaseTemplateEO;
import com.lcw.one.sys.service.BaseTemplateEOService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/base/template")
@Api(description = "系统模板")
public class BaseTemplateEORestController {

    @Autowired
    private BaseTemplateEOService baseTemplateEOService;

    @ApiOperation(value = "系统模板详情")
    @GetMapping("/{id}")
    public ResponseMessage<BaseTemplateEO> getById(@PathVariable String id) {
        return Result.success(baseTemplateEOService.get(id));
    }

    @ApiOperation(value = "系统模板列表")
    @GetMapping("")
    public ResponseMessage<List<BaseTemplateEO>> list(@RequestParam String templateTypeCode) {
        return Result.success(baseTemplateEOService.list(templateTypeCode));
    }

    @ApiOperation(value = "新增系统模板")
    @PostMapping("")
    public ResponseMessage<BaseTemplateEO> save(@RequestBody BaseTemplateEO baseTemplateEO) {
        return Result.success(baseTemplateEOService.save(baseTemplateEO));
    }

    @ApiOperation(value = "修改系统模板")
    @PutMapping("")
    public ResponseMessage<BaseTemplateEO> update(@RequestBody BaseTemplateEO baseTemplateEO) {
        return Result.success(baseTemplateEOService.update(baseTemplateEO));
    }

    @ApiOperation(value = "删除系统模板")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@PathVariable String id) {
        baseTemplateEOService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "填充模板")
    @PostMapping("/{templateTypeCode}/fill")
    public ResponseMessage<String> fill(String templateTypeCode, @RequestBody Map<String, Object> paramMap) {
        return Result.success(baseTemplateEOService.fillTemplateByCode(templateTypeCode, paramMap));
    }
}
