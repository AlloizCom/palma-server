package com.alloiz.palma.server.config.websocket;


import com.alloiz.palma.server.config.websocket.interseptor.HttpSessionIdHandshakeInterceptor;
import com.alloiz.palma.server.config.websocket.interseptor.SessionKeepAliveChannelInterceptor;
import com.alloiz.palma.server.config.websocket.interseptor.WebSocketSessionCapturingHandlerDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.util.TreeMap;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/booking","/callback");
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(sessionKeepAliveChannelInterceptor());
    }
    @Bean
    public HttpSessionIdHandshakeInterceptor httpSessionIdHandshakeInterceptor() {
        return new HttpSessionIdHandshakeInterceptor();
    }
    @Bean
    public SessionRepository sessionRepository() {
        return new MapSessionRepository(new TreeMap<>());
    }
    @Bean
    public SessionKeepAliveChannelInterceptor sessionKeepAliveChannelInterceptor() {
        return new SessionKeepAliveChannelInterceptor();
    }

    @Autowired
    private WebSocketHandler subProtocolWebSocketHandler;

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(WebSocketHandler webSocketHandler) {
                return new WebSocketSessionCapturingHandlerDecorator(webSocketHandler);
            }
        });
    }
}