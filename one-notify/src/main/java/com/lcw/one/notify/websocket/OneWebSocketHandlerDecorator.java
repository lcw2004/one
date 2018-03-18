package com.lcw.one.notify.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

/**
 * @version 2017-11-08.
 * @auth Licw
 */
public class OneWebSocketHandlerDecorator extends WebSocketHandlerDecorator {

    private static final Logger logger = LoggerFactory.getLogger(OneWebSocketHandlerDecorator.class);

    public OneWebSocketHandlerDecorator(WebSocketHandler delegate) {
        super(delegate);
    }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        String principalId = (String) session.getAttributes().get("principalId");
        String sessionId = session.getId();

        // 客户端与服务器端建立连接后，此处记录谁上线了
        logger.info("WebSocket Online, Principal Id[{}], Session Id[{}]", principalId, sessionId);
        SocketSessionRegistry.registerSessionId(principalId, sessionId);

        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("WebSocket handleTransportError");
        logger.error(exception.getMessage(), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String principalId = (String) session.getAttributes().get("principalId");
        String sessionId = session.getId();

        // 客户端与服务器端建立连接后，此处记录谁下线了
        logger.info("WebSocket Offline, Principal Id[{}], Session Id[{}]", principalId, sessionId);
        SocketSessionRegistry.unRegisterSessionId(principalId, sessionId);

        super.afterConnectionClosed(session, closeStatus);
    }
}
