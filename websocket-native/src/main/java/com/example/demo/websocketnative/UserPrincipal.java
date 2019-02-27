package com.example.demo.websocketnative;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Principal;

/**
 * TODO
 *
 * @author lsk
 * @class_name UserPrincipal
 * @date 2019/2/27
 */
@Getter
@Setter
@NoArgsConstructor
public class UserPrincipal implements Principal {

    private String user;

    public UserPrincipal(String user){
        this.user = user;
    }

    @Override
    public String getName() {
        return user;
    }
}
