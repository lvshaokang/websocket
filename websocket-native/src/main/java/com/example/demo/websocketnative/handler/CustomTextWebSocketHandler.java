package com.example.demo.websocketnative.handler;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author lsk
 * @class_name CustomWebSocketHandler
 * @date 2019/2/26
 */
@Slf4j
@NoArgsConstructor
@Component
public class CustomTextWebSocketHandler extends TextWebSocketHandler {


    // 保存用户连接
    private static ConcurrentHashMap<String, WebSocketSession> users = new ConcurrentHashMap<>();

    /**
     * 连接就绪时
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established...");

        String user = (String) session.getAttributes().get("user");
        users.put(user, session);

        log.info("在线人数: " + users.size());


    }


    /**
     * 连接错误时触发
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String user = (String) session.getAttributes().get("user");
        if (session.isOpen()) {
            session.close();
        }
        log.info("Connection closed...");
        users.remove(user);
    }

    /**
     * 关闭连接时
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String user = (String) session.getAttributes().get("user");
        log.info("Connection closed...");

        users.remove(user);
        log.info("在线人数: " + users.size());
    }

    /**
     * 处理信息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Received message: " + message.getPayload());

        sendMessageToUser("xxx", new TextMessage("xxx，你来啦"));

        batchSendMessage(new TextMessage("感谢连接"));


    }

    /**
     * 是否支持分包
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 发送给指定用户
     */
    public void sendMessageToUser(String userId, TextMessage message) {
        WebSocketSession session = users.get(userId);
        if (session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 群发
     */
    public void batchSendMessage(TextMessage message) {
        Set<Map.Entry<String, WebSocketSession>> sets = users.entrySet();
        sets.forEach(x -> {
            if (x.getValue().isOpen()) {
                try {
                    x.getValue().sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
