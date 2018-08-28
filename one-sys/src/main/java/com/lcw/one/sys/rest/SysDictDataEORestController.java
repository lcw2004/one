package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysDictDataEO;
import com.lcw.one.sys.service.SysDictDataEOService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/sys/dict")
@Api(description = "系统 - 字典值")
public class SysDictDataEORestController {

    @Autowired
    private SysDictDataEOService sysDictDataEOService;

    @ApiOperation(value = "字典值查询")
    @GetMapping("/{dictId}/data")
    public ResponseMessage<List<SysDictDataEO>> list(@PathVariable String dictId) {
        return Result.success(sysDictDataEOService.list(dictId));
    }

    @ApiOperation(value = "新增字典")
    @PostMapping("/{dictId}/data")
    public ResponseMessage<SysDictDataEO> save(@PathVariable String dictId, @RequestBody SysDictDataEO sysDictDataEO) {
        return Result.success(sysDictDataEOService.save(dictId, sysDictDataEO));
    }

    @ApiOperation(value = "修改字典")
    @PutMapping("/{dictId}/data")
    public ResponseMessage<SysDictDataEO> update(@PathVariable String dictId, @RequestBody SysDictDataEO sysDictDataEO) {
        return Result.success(sysDictDataEOService.update(sysDictDataEO));
    }

    @ApiOperation(value = "删除字典")
    @DeleteMapping("/{dictId}/data/{dataId}")
    public ResponseMessage deleteById(@PathVariable String dataId) {
        sysDictDataEOService.delete(dataId);
        return Result.success();
    }
}
