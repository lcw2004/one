package com.lcw.one.notify.config;

import com.lcw.one.notify.websocket.HandShakeInterceptor;
import com.lcw.one.notify.websocket.OneChannelInterceptor;
import com.lcw.one.notify.websocket.OneWebSocketHandlerDecorator;
import com.lcw.one.notify.websocket.SocketSessionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/broker");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/one-websocket")
                .addInterceptors(new HandShakeInterceptor())
                .setAllowedOrigins("*")
                .withSockJS()
                // TODO 需要跟客户端的版本保持一致，如果是内网的话，需要修改配置
                .setClientLibraryUrl("http://cdn.jsdelivr.net/sockjs/1/sockjs.min.js");
    }

    @Bean
    public SocketSessionRegistry socketSessionRegistry(){
        return new SocketSessionRegistry();
    }

    @Bean
    public OneChannelInterceptor myChannelInterceptor(){
        return new OneChannelInterceptor();
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        ChannelRegistration channelRegistration = registration.setInterceptors(myChannelInterceptor());
//        super.configureClientInboundChannel(channelRegistration);
//    }

//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration registration) {
//        super.configureClientOutboundChannel(registration);
//    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(WebSocketHandler handler) {
                return new OneWebSocketHandlerDecorator(handler);
            }
        });
        super.configureWebSocketTransport(registration);
    }


}