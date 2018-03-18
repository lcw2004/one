package com.lcw.one.codegen.rest;

import com.lcw.one.codegen.entity.CodeGenTableFieldEO;
import com.lcw.one.codegen.service.CodeGenTableFieldEOService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/codeGen/tableEO")
@Api(description = "表字段")
public class CodeGenTableFieldEORestController {

    @Autowired
    private CodeGenTableFieldEOService codeGenTableFieldEOService;

    @ApiOperation(value = "表字段列表")
    @GetMapping("/{tableId}/field")
    public ResponseMessage<List<CodeGenTableFieldEO>> list(@PathVariable("tableId") String tableId) {
        return Result.success(codeGenTableFieldEOService.listByTableId(tableId));
    }

    @ApiOperation(value = "表字段修改")
    @PutMapping("/{tableId}/field")
    public ResponseMessage update(@PathVariable("tableId") String tableId, @RequestBody List<CodeGenTableFieldEO> codeGenTableFieldEO) {
        codeGenTableFieldEOService.update(codeGenTableFieldEO);
        return Result.success();
    }

}
