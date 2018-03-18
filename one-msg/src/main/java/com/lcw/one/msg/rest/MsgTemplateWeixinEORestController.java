package com.lcw.one.msg.rest;

import com.lcw.one.msg.entity.MsgTemplateWeixinEO;
import com.lcw.one.msg.service.MsgTemplateWeixinEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/msg/")
@Api(description = "微信消息模板")
public class MsgTemplateWeixinEORestController {

    @Autowired
    private MsgTemplateWeixinEOService msgTemplateWeixinEOService;

    @ApiOperation(value = "微信消息模板详情")
    @GetMapping("/{id}")
    public ResponseMessage<MsgTemplateWeixinEO> get(@PathVariable("id") String id) {
        return Result.success(msgTemplateWeixinEOService.get(id));
    }

    @ApiOperation(value = "微信消息模板列表")
    @GetMapping("")
    public ResponseMessage<List<MsgTemplateWeixinEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(msgTemplateWeixinEOService.findAll());
    }

    @ApiOperation(value = "新增微信消息模板")
    @PostMapping("")
    public ResponseMessage<MsgTemplateWeixinEO> save(@RequestBody MsgTemplateWeixinEO msgTemplateWeixinEO) {
        return Result.success(msgTemplateWeixinEOService.save(msgTemplateWeixinEO));
    }

    @ApiOperation(value = "修改微信消息模板")
    @PutMapping("")
    public ResponseMessage<MsgTemplateWeixinEO> update(@RequestBody MsgTemplateWeixinEO msgTemplateWeixinEO) {
        return Result.success(msgTemplateWeixinEOService.update(msgTemplateWeixinEO));
    }

    @ApiOperation(value = "删除微信消息模板")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") String id) {
        msgTemplateWeixinEOService.delete(id);
        return Result.success();
    }

}