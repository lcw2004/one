package com.lcw.one.notify.rest;

import com.lcw.one.notify.bean.MessageBean;
import com.lcw.one.notify.channel.base.WebSocketService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.GsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/${restPath}/notify/broker")
@Api(description = "消息 - 广播消息")
public class WSBrokerRestController {

    private static final Logger logger = LoggerFactory.getLogger(WSBrokerRestController.class);

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 广播消息
     *
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/hello")
    @SendTo("/broker/notice")
    public ResponseMessage<String> notice(String message) throws Exception {
        logger.info("Receive Message : " + GsonUtil.toJson(message));

        Thread.sleep(300); // simulated delay
        return Result.success("Hello, " + message + "! This is global notice from server");
    }

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage<String> say(String message) throws Exception {
        logger.info("Receive Message : " + GsonUtil.toJson(message));

        Thread.sleep(300);
        return Result.success("this is welcome");
    }


    @ApiOperation(value = "测试广播消息")
    @GetMapping("/notice")
    public ResponseMessage testNotice() {
        webSocketService.sendToAll(new MessageBean("Hello! This is global notice from server"));
        return Result.success();
    }

    @ApiOperation(value = "测试广播消息")
    @GetMapping("/noticeByDestination")
    public ResponseMessage testNotice(String destination, String message) {
        webSocketService.sendToAll(destination, new MessageBean(message));
        return Result.success();
    }

    @ApiOperation(value = "测试广播消息")
    @GetMapping("/notice/ResponseMessage")
    public ResponseMessage testNoticeResponseMessage(String destination, String message) {
        webSocketService.sendToAll(destination, Result.success(message));
        return Result.success();
    }

    @ApiOperation(value = "测试广播消息")
    @GetMapping("/toUser")
    public ResponseMessage testToUser(String userId, String destination, String  message) {
        webSocketService.sentToUser(destination, new MessageBean(message), userId);
        return Result.success();
    }
}
