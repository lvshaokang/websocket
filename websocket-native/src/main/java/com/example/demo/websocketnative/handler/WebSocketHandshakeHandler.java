package com.example.demo.websocketnative.handler;

import com.example.demo.websocketnative.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * TODO
 *
 * @author lsk
 * @class_name WebSokcetHandshakeHandler
 * @date 2019/2/27
 */
@Slf4j
@Component
public class WebSocketHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String user = (String) attributes.get("user");

        if (user == null){
            return null;
        }

        return new UserPrincipal(user);
    }
}
