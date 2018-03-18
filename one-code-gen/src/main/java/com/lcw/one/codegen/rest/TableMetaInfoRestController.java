package com.lcw.one.codegen.rest;

import com.lcw.one.codegen.bean.CodeBean;
import com.lcw.one.codegen.bean.CodeGenStrategy;
import com.lcw.one.codegen.bean.ModuleBean;
import com.lcw.one.codegen.service.CodeGenerateService;
import com.lcw.one.codegen.service.OnePomService;
import com.lcw.one.codegen.service.TableMetaInfoService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "${restPath}/codeGen/table")
@Api(description = "表元数据接口")
public class TableMetaInfoRestController {

    @Autowired
    private TableMetaInfoService tableMetaInfoService;

    @Autowired
    private OnePomService onePomService;

    @Autowired
    private CodeGenerateService codeGenerateService;

    @ApiOperation(value = "获取表元数据")
    @GetMapping("")
    @ResponseBody
    public ResponseMessage listTable(String tableName) {
        return Result.success(tableMetaInfoService.listTableName(tableName));
    }


    @ApiOperation(value = "获取表字段元数据")
    @GetMapping("/{tableName}")
    @ResponseBody
    public ResponseMessage getTable(@PathVariable("tableName") String tableName) {
        return Result.success(tableMetaInfoService.getTableByTableName(tableName));
    }

    @ApiOperation(value = "预览代码")
    @PostMapping("/{tableName}/{codeType}")
    @ResponseBody
    public ResponseMessage<List<CodeBean>> previewCode(@PathVariable("tableName") String tableName, @PathVariable("codeType") String codeType, @RequestBody CodeGenStrategy codeGenStrategy) {
        return Result.success(codeGenerateService.codePreview(tableName, codeType, codeGenStrategy));
    }

    @ApiOperation(value = "列出所有的Pom模块")
    @GetMapping("/module")
    @ResponseBody
    public ResponseMessage<List<ModuleBean>> listModules() {
        return Result.success(onePomService.listModules());
    }

    @ApiOperation(value = "生成代码")
    @PostMapping("/generate")
    @ResponseBody
    public ResponseMessage generate(@RequestBody CodeGenStrategy codeGenStrategy) {
        codeGenerateService.generate(codeGenStrategy);
        return Result.success();
    }

}

