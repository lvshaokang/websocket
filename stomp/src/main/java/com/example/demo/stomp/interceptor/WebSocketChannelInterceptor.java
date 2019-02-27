package com.example.demo.stomp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;


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

        SimpMessageHeaderAccessor simpMessageHeaderAccessor = SimpMessageHeaderAccessor.wrap(message);

        SimpMessageType messageType = SimpMessageHeaderAccessor.getMessageType(message.getHeaders());

        if (SimpMessageType.CONNECT.equals(messageType)) {
            // TODO:　TOKEN 鉴权
        }

        return message;
    }
}
