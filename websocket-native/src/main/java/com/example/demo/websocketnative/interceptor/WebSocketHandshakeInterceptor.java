package com.example.demo.websocketnative.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * TODO
 *
 * @author lsk
 * @class_name WebSocketHandlerInterceptor
 * @date 2019/2/27
 */
@Slf4j
@Component
public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("web-socket starting handshake...");
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String username = servletRequest.getParameter("user");
        attributes.put("user",username);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info("web-socket handshake success!");

        super.afterHandshake(request, response, wsHandler, ex);
    }
}
