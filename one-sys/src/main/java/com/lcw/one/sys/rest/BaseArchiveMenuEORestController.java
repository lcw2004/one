package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.BaseArchiveMenuEO;
import com.lcw.one.sys.service.BaseArchiveMenuEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/base/archiveMenu")
@Api(description = "归档目录")
public class BaseArchiveMenuEORestController {

    @Autowired
    private BaseArchiveMenuEOService baseArchiveMenuEOService;

    @ApiOperation(value = "归档类别详情")
    @GetMapping("/{id}")
    public ResponseMessage<BaseArchiveMenuEO> getById(@PathVariable("id") String id) {
        return Result.success(baseArchiveMenuEOService.get(id));
    }

    @ApiOperation(value = "归档类别列表")
    @GetMapping("")
    public ResponseMessage<List<BaseArchiveMenuEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(baseArchiveMenuEOService.findAll());
    }

    @ApiOperation(value = "归档类别树")
    @GetMapping(value = "/tree")
    public ResponseMessage<BaseArchiveMenuEO> listAsTree() {
        List<BaseArchiveMenuEO> list = baseArchiveMenuEOService.findAll();
        BaseArchiveMenuEO topElement = baseArchiveMenuEOService.organizeListAsTree(baseArchiveMenuEOService.get("1"), list);
        return Result.success(topElement);
    }

    @ApiOperation(value = "新增归档类别")
    @PostMapping("")
    public ResponseMessage<BaseArchiveMenuEO> save(@RequestBody BaseArchiveMenuEO baseArchiveMenuEO) {
        return Result.success(baseArchiveMenuEOService.save(baseArchiveMenuEO));
    }

    @ApiOperation(value = "修改归档类别")
    @PutMapping("")
    public ResponseMessage<BaseArchiveMenuEO> update(@RequestBody BaseArchiveMenuEO baseArchiveMenuEO) {
        return Result.success(baseArchiveMenuEOService.update(baseArchiveMenuEO));
    }

    @ApiOperation(value = "删除归档类别")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@PathVariable("id") String id) {
        baseArchiveMenuEOService.delete(id);
        return Result.success();
    }

}
