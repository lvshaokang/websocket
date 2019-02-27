package com.example.demo.stomp.service;

import com.example.demo.stomp.message.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author lsk
 * @class_name WebSocketService
 * @date 2019/2/20
 */
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendMessage(String topic, Response response) throws InterruptedException {
        template.convertAndSend(topic,response);

    }
}
