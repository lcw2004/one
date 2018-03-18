package com.lcw.one.notify.rest;

import com.lcw.one.notify.service.IMessageSenderService;
import com.lcw.one.notify.service.impl.SimpleMessageSenderServiceImpl;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/message/sender")
@Api(description = "消息 - 发送消息")
public class MsgSenderRestController {

    private static final Logger logger = LoggerFactory.getLogger(MsgSenderRestController.class);

    @Autowired
    private IMessageSenderService msgSenderService;

    @ApiOperation(value = "发送消息到指定用户")
    @PostMapping("/byUserId")
    public ResponseMessage sendMessageByUserId(String[] userIdList, String templateId, @RequestBody Map<String, Object> placeholderMap) {
        msgSenderService.sendMessage(Arrays.asList(userIdList), templateId, placeholderMap);
        return Result.success();
    }

    @ApiOperation(value = "发送消息到指定用户")
    @PostMapping("/byRoleId")
    public ResponseMessage sendMessageByRoleId(String[] roleIdList, String templateId, @RequestBody Map<String, Object> placeholderMap) {
        msgSenderService.sendMessageByRoleId(Arrays.asList(roleIdList), templateId, placeholderMap);
        return Result.success();
    }

    @ApiOperation(value = "发送消息到指定用户")
    @PostMapping("/byOfficeId")
    public ResponseMessage sendMessageByOfficeId(String[] officeIdList, String templateId, @RequestBody Map<String, Object> placeholderMap) {
        msgSenderService.sendMessageByOfficeId(Arrays.asList(officeIdList), templateId, placeholderMap);
        return Result.success();
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping("/sendEmail")
    public ResponseMessage sendEmail(String email, String title, String content) {
        msgSenderService.sendEmail(email, title, content);
        return Result.success();
    }


    @ApiOperation(value = "发送邮件")
    @PostMapping("/sendSMS")
    public ResponseMessage sendSMS(String mobile, String content) {
        msgSenderService.sendSMS(mobile, content);
        return Result.success();
    }


    @ApiOperation(value = "发送WebSocket")
    @PostMapping("/sendWebSocket")
    public ResponseMessage sendWebSocket() {
        msgSenderService.sendWebSocket();
        return Result.success();
    }

}
