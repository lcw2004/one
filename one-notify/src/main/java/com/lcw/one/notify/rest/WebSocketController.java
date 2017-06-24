package com.lcw.one.notify.rest;

import com.lcw.one.notify.config.QuickCodeMessage;
import com.lcw.one.notify.config.QuickCodesResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public QuickCodesResponse say(QuickCodeMessage message) throws Exception {
        Thread.sleep(1000);
        return new QuickCodesResponse("this is welcome");
    }

}
