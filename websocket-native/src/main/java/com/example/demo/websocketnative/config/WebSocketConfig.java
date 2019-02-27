package com.example.demo.websocketnative.config;

import com.example.demo.websocketnative.handler.CustomTextWebSocketHandler;
import com.example.demo.websocketnative.handler.WebSocketHandshakeHandler;
import com.example.demo.websocketnative.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * TODO
 *
 * @author lsk
 * @class_name WebSocketConfig
 * @date 2019/2/26
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler(), "/websocket")
                .addInterceptors(handshakeInterceptor()).setHandshakeHandler(handshakeHandler()).setAllowedOrigins("*");
    }

    @Bean
    public TextWebSocketHandler webSocketHandler() {
        return new CustomTextWebSocketHandler();
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new WebSocketHandshakeInterceptor();
    }

    @Bean
    public HandshakeHandler handshakeHandler() {
        return new WebSocketHandshakeHandler();
    }

}
