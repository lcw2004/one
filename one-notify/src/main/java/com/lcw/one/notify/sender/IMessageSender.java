package com.lcw.one.notify.sender;

import com.lcw.one.msg.entity.MsgMessageEO;

import java.util.List;

public interface IMessageSender {

    void send(List<String> userIdList, MsgMessageEO msgMessageEO);

}
