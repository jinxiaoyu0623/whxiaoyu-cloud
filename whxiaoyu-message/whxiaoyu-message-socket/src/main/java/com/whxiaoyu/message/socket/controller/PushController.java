package com.whxiaoyu.message.socket.controller;

import com.whxiaoyu.message.socket.handler.PushMsgHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
public class PushController {

    private final PushMsgHandler pushMsgHandler;

    @GetMapping("/push")
    public String push(String message) throws Exception {
        pushMsgHandler.pushMessage(message);
        return "ok";
    }

}
