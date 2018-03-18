package com.lcw.one.codegen.rest;

import io.swagger.annotations.ApiOperation;

import com.lcw.one.codegen.entity.CodeGenTableEO;
import com.lcw.one.codegen.service.CodeGenTableEOService;
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
@RequestMapping(value = "/${restPath}/codeGen/tableEO")
@Api(description = "表元数据")
public class CodeGenTableEORestController {

    @Autowired
    private CodeGenTableEOService codeGenTableEOService;

    @ApiOperation(value = "表元数据列表")
    @GetMapping("")
    public ResponseMessage<List<CodeGenTableEO>> list(String[] tableList) {
        return Result.success(codeGenTableEOService.list(tableList));
    }

    @ApiOperation(value = "表元数据修改")
    @PutMapping("")
    public ResponseMessage<CodeGenTableEO> update(@RequestBody CodeGenTableEO codeGenTableEO) {
        return Result.success(codeGenTableEOService.update(codeGenTableEO));
    }

}
