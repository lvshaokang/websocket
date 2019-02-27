package com.example.demo.stomp.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TODO
 *
 * @author lsk
 * @class_name Response
 * @date 2019/2/20
 */
@Setter
@Getter
@NoArgsConstructor
public class Response {

    private String respMsg;

    public Response(String respMsg){
        this.respMsg = respMsg;
    }

}
