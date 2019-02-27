package com.example.demo.stomp.config;

import com.example.demo.stomp.interceptor.WebSocketChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * TODO
 *
 * @author lsk
 * @class_name WebSocketConfig
 * @date 2019/2/20
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws").setAllowedOrigins("*")
                .withSockJS();

        // 点对点
        registry.addEndpoint("/ws/point").withSockJS();
    }

    /**
     * 配置消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 广播式
        registry.enableSimpleBroker("/topic");

        // 点对点
        registry.enableSimpleBroker("/topic", "/user");

        // 区分websocket和普通请求
        registry.setApplicationDestinationPrefixes("/app");

        registry.setUserDestinationPrefix("/user");
    }

    /**
     * 拦截器配置
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new WebSocketChannelInterceptor());

    }
}
