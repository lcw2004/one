package com.lcw.one.notify.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

public class OneChannelInterceptor extends ChannelInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(OneChannelInterceptor.class);

    @Override
    public boolean preReceive(MessageChannel channel) {
        logger.info("preReceive");
        return super.preReceive(channel);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        logger.info("preSend");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        //检测用户订阅内容（防止用户订阅不合法频道）
        if (StompCommand.SUBSCRIBE.equals(command)) {
            return super.preSend(message, channel);
        } else {
            return super.preSend(message, channel);
        }
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        logger.info("afterSendCompletion");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (StompCommand.SUBSCRIBE.equals(command)) {

        }
        if (StompCommand.DISCONNECT.equals(command)) {

        }
        super.afterSendCompletion(message, channel, sent, ex);
    }

}