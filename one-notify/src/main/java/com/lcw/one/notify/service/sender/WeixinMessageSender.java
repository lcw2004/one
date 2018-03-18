package com.lcw.one.notify.service.sender;

import com.lcw.one.msg.entity.MsgMessageEO;
import com.lcw.one.notify.service.IMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeixinMessageSender implements IMessageSender {

    private static Logger logger = LoggerFactory.getLogger(WeixinMessageSender.class);

    @Override
    public void send(List<String> userIdList, MsgMessageEO msgMessageEO) {

    }
}
