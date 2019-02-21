package com.example.demo.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * TODO
 *
 * @author lsk
 * @class_name WebSocketChannelInterceptor
 * @date 2019/2/21
 */
@Slf4j
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("preSend -> 3");

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())){
            String token = accessor.getNativeHeader("Auth-Token").get(0);
            log.info("Token -> " + token);

            // TODO:　TOKEN 鉴权

            throw new RuntimeException("xxxx");

//            Token user = (Token) accessor.getUser();

        }
        return message;
    }
}
