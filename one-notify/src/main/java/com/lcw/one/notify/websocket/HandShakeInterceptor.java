package com.lcw.one.notify.websocket;

import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.base.utils.LoginUserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * WebScoket 握手过滤器
 */
public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HandShakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info("WebSocket Handshake Before: " + request.getURI());
        try {
            if (request instanceof ServletServerHttpRequest) {
                ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

                // 获取凭证ID，检查用户是否登录
                String principalId = getPrincipalId(servletRequest.getServletRequest());

                // 从Session中获取CookieId（无法从Header中获取）
                String token = LoginUserUtils.getTokenFromSession(servletRequest.getServletRequest());

                logger.debug("WebSocket PrincipalId [{}]", principalId);
                logger.debug("WebSocket Token [{}]", token);
                attributes.put("principalId", principalId);
                attributes.put("token", token);
                return super.beforeHandshake(request, response, wsHandler, attributes);
            }
        } catch (LoginInvalidException e) {
            logger.warn("WebSocket Handshake Error(登录失效，握手失败)");
            return false;
        } catch (Exception e) {
            logger.warn("WebSocket Handshake Error({})", e.getMessage());
            logger.error(e.getMessage(), e);
            return false;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        //握手成功后，通常用来注册用户信息
        logger.info("WebSocket Handshake After");
        super.afterHandshake(request, response, wsHandler, ex);
    }

    /**
     * 获取凭证ID
     * 1. 供应商：如果是供应商则用供应商ID作为凭证，不用供应商的登录用户作为凭证
     * 2. 非供应商：非供应商用户都以用户ID作为凭证
     * @return
     */
    private static String getPrincipalId(HttpServletRequest request) {
        // 获取用户ID，检查用户是否登录
        String principalId = "";
        LoginUser loginUser = LoginUserUtils.getCurrentUser(LoginUserUtils.getTokenFromSession(request));
        if (loginUser != null) {
            if (loginUser.getUserType() == UserInfoTypeEnum.SUPPLIER.getValue()) {
                principalId = loginUser.getSupplierId();
            } else {
                principalId = loginUser.getUserId();
            }
        }
        return principalId;
    }

}