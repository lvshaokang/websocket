package com.example.demo.stomp.controller;

import com.example.demo.stomp.message.Message;
import com.example.demo.stomp.message.Response;
import com.example.demo.stomp.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Springboot-websocket-stomp
 *
 * @author lsk
 * @class_name WebsocketController
 * @date 2019/2/20
 */
@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/send")
    public void send0(Message message, @Header("token") String token) throws InterruptedException {
        log.info("群聊 -> 服务器 -> 接收的消息为: " + message.getName());
        log.info("websocket请求头 -> token : " + token);
        template.convertAndSend("/topic/subscribe", new Response("Welcome, " + message.getName() + "！"));
    }

    @SubscribeMapping("/topic/subscribe")
    public Response subscribe0() {
        return new Response("订阅成功");
    }

    @SubscribeMapping("/user/subscribe")
    public Response subscribe1() {
        return new Response("订阅成功");
    }

    @MessageMapping("/send1")
    public void send1(Message message, @Header("userId") String userId) throws InterruptedException {
        log.info("一对一 -> 服务器 -> 接收的消息为: " + message.getName());
        log.info("websocket请求头 -> userId : " + userId);

        template.convertAndSendToUser(userId, "/user/subscribe", new Response("Welcome, " + message.getName() + "！"));
    }

    @GetMapping("/ws")
    public String ws() {
        return "ws";
    }
}
