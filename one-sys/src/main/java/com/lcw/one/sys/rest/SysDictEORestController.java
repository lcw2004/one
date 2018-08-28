package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysDictEO;
import com.lcw.one.sys.service.SysDictEOService;
import com.lcw.one.base.utils.DictUtils;
import com.lcw.one.util.bean.SysDict;
import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/sys/dict")
@Api(description = "系统 - 字典")
public class SysDictEORestController {

    @Autowired
    private SysDictEOService sysDictEOService;

    @ApiOperation(value = "字典详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysDictEO> getById(@PathVariable String id) {
        return Result.success(sysDictEOService.get(id));
    }

    @ApiOperation(value = "字典查询")
    @GetMapping("")
    public ResponseMessage<PageInfo<SysDictEO>> list(Integer pageNo, Integer pageSize, String type, String name) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(sysDictEOService.page(pageInfo, type, name));
    }

    @ApiOperation(value = "新增字典")
    @PostMapping("")
    public ResponseMessage<SysDictEO> save(@RequestBody SysDictEO sysMenuEO) {
        sysMenuEO.setDictId(UUID.randomUUID10());
        sysMenuEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        return Result.success(sysDictEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改字典")
    @PutMapping("")
    public ResponseMessage<SysDictEO> update(@RequestBody SysDictEO sysMenuEO) {
        return Result.success(sysDictEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除字典")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@PathVariable String id) {
        sysDictEOService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "获取所有字典类型")
    @GetMapping("/type")
    public ResponseMessage<List<String>> listDictType() {
        return Result.success(sysDictEOService.listDictType());
    }

    @ApiOperation(value = "以Map形式获取所有字典")
    @RequestMapping(value = "/group", method = RequestMethod.GET, produces = "application/json")
    public ResponseMessage<Map<String, List<SysDict>>> groupList() {
        return Result.success(DictUtils.getDictMap());
    }
}
