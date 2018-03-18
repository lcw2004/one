package com.lcw.one.msg.rest;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.msg.bean.MsgMessageVO;
import com.lcw.one.msg.entity.MsgMessageEO;
import com.lcw.one.msg.service.MsgMessageEOService;
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
@RequestMapping(value = "/${restPath}/msg/message")
@Api(description = "消息")
public class MsgMessageEORestController {

    @Autowired
    private MsgMessageEOService msgMessageEOService;

    @ApiOperation(value = "消息列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<MsgMessageVO>> list(HttpServletRequest request, String status) {
        PageInfo<MsgMessageVO> msgMessageEOList = msgMessageEOService.pageByUserId(new PageInfo(request), LoginUserUtils.getLoginUserIdWithoutFlush(request), status);
        return Result.success(msgMessageEOList);
    }

    @ApiOperation(value = "将消息标为已读")
    @PutMapping("/read")
    public ResponseMessage markAsRead(HttpServletRequest request, String[] messageIdList) {
        msgMessageEOService.markAsRead(Arrays.asList(messageIdList), LoginUserUtils.getLoginUserId(request));
        return Result.success();
    }

    @ApiOperation(value = "将消息标为删除")
    @PutMapping("/delete")
    public ResponseMessage markAsDelete(HttpServletRequest request, String[] messageIdList) {
        msgMessageEOService.markAsDelete(Arrays.asList(messageIdList), LoginUserUtils.getLoginUserId(request));
        return Result.success();
    }

    @ApiOperation(value = "将消息标为已读")
    @PutMapping("/readAll")
    public ResponseMessage markAllAsRead(HttpServletRequest request) {
        msgMessageEOService.markAllAsRead(LoginUserUtils.getLoginUserId(request));
        return Result.success();
    }

    @ApiOperation(value = "将消息标为删除")
    @PutMapping("/deleteAll")
    public ResponseMessage markAllAsDelete(HttpServletRequest request) {
        msgMessageEOService.markAllAsDelete(LoginUserUtils.getLoginUserId(request));
        return Result.success();
    }

}