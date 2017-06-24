package com.lcw.one.notify.rest;

import com.lcw.one.notify.ws.GetHttpSessionConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/ws/chat", configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatWebSocket {

    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocket.class);

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<ChatWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("有新链接加入!当前在线人数为" + getOnlineCount());
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());


        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
    }

    @OnClose
    public void onClose() {
        logger.info("有一链接关闭!当前在线人数为" + getOnlineCount());
        webSocketSet.remove(this);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("来自客户端的消息:" + message);

        // 群发消息
        for (ChatWebSocket item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return ChatWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChatWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatWebSocket.onlineCount--;
    }
}
