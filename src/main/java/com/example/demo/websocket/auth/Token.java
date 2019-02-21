package com.example.demo.websocket.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Principal;

/**
 * TODO
 *
 * @author lsk
 * @class_name Token
 * @date 2019/2/21
 */
@NoArgsConstructor
@Getter
@Setter
public class Token implements Principal {

    private String token;

    @Override
    public String getName() {
        return null;
    }

    public Token(String token){
        this.token = token;
    }
}
