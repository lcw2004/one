package com.lcw.one.msg.rest;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.msg.constant.MsgAnnounceStatusEnum;
import com.lcw.one.msg.entity.MsgAnnounceEO;
import com.lcw.one.msg.service.MsgAnnounceEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/msg/announce")
@Api(description = "公告")
public class MsgAnnounceEORestController {

    @Autowired
    private MsgAnnounceEOService msgAnnounceEOService;

    @ApiOperation(value = "公告详情")
    @GetMapping("/{announceId}")
    public ResponseMessage<MsgAnnounceEO> get(@PathVariable String announceId) {
        return Result.success(msgAnnounceEOService.get(announceId));
    }

    @ApiOperation(value = "公告列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<MsgAnnounceEO>> list(HttpServletRequest request, String likeName) {
        return Result.success(msgAnnounceEOService.page(new PageInfo(request), likeName));
    }

    @ApiOperation(value = "公告列表")
    @GetMapping("/forRead")
    public ResponseMessage<PageInfo<MsgAnnounceEO>> listForRead(HttpServletRequest request, String likeName) {
        return Result.success(msgAnnounceEOService.pageForRead(new PageInfo(request), LoginUserUtils.getCurrentUser(request), likeName));
    }

    @ApiOperation(value = "新增公告")
    @PostMapping("")
    public ResponseMessage<MsgAnnounceEO> save(HttpServletRequest request, @RequestBody MsgAnnounceEO msgAnnounceEO) {
        msgAnnounceEO.setCreateUserId(LoginUserUtils.getLoginUserId(request));
        return Result.success(msgAnnounceEOService.save(msgAnnounceEO));
    }

    @ApiOperation(value = "修改公告")
    @PutMapping("")
    public ResponseMessage<MsgAnnounceEO> update(@RequestBody MsgAnnounceEO msgAnnounceEO) {
        return Result.success(msgAnnounceEOService.update(msgAnnounceEO));
    }

    @ApiOperation(value = "发布公告")
    @PutMapping("/publish")
    public ResponseMessage<MsgAnnounceEO> publish(@RequestBody MsgAnnounceEO msgAnnounceEO) {
        return Result.success(msgAnnounceEOService.publish(msgAnnounceEO));
    }

    @ApiOperation(value = "删除公告")
    @DeleteMapping("/{announceId}")
    public ResponseMessage delete(@PathVariable String announceId) {
        msgAnnounceEOService.delete(announceId);
        return Result.success();
    }

    @ApiOperation(value = "启用公告")
    @PutMapping("/{announceId}/start")
    public ResponseMessage start(@PathVariable String announceId) {
        msgAnnounceEOService.updateStatus(announceId, MsgAnnounceStatusEnum.STARTED.getValue());
        return Result.success();
    }

    @ApiOperation(value = "关闭公告")
    @PutMapping("/{announceId}/close")
    public ResponseMessage close(@PathVariable String announceId) {
        msgAnnounceEOService.updateStatus(announceId, MsgAnnounceStatusEnum.CLOSED.getValue());
        return Result.success();
    }

    @ApiOperation(value = "置顶公告")
    @PutMapping("/{announceId}/top")
    public ResponseMessage top(@PathVariable String announceId) {
        msgAnnounceEOService.updateIsTop(announceId, 1);
        return Result.success();
    }

    @ApiOperation(value = "取消置顶公告")
    @PutMapping("/{announceId}/unTop")
    public ResponseMessage unTop(@PathVariable String announceId) {
        msgAnnounceEOService.updateIsTop(announceId, 0);
        return Result.success();
    }

    @ApiOperation(value = "阅读公告")
    @PutMapping("/{announceId}/read")
    public ResponseMessage read(@PathVariable String announceId) {
        msgAnnounceEOService.increaseReadCount(announceId);
        return Result.success();
    }

    @ApiOperation(value = "删除公告")
    @PutMapping("/delete")
    public ResponseMessage delete(String[] announceIdList) {
        msgAnnounceEOService.delete(announceIdList);
        return Result.success();
    }

    @ApiOperation(value = "启用公告")
    @PutMapping("/start")
    public ResponseMessage start(String[] announceIdList) {
        msgAnnounceEOService.updateStatus(Arrays.asList(announceIdList), MsgAnnounceStatusEnum.STARTED.getValue());
        return Result.success();
    }

    @ApiOperation(value = "关闭公告")
    @PutMapping("/close")
    public ResponseMessage close(String[] announceIdList) {
        msgAnnounceEOService.updateStatus(Arrays.asList(announceIdList), MsgAnnounceStatusEnum.CLOSED.getValue());
        return Result.success();
    }

}