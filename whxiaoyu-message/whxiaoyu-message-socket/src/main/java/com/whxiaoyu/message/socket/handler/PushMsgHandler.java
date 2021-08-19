package com.whxiaoyu.message.socket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 推送服务
 * @author jinxiaoyu
 */
@Slf4j
public class PushMsgHandler implements WebSocketHandler {

    private static ConcurrentHashMap<String,WebSocketSession> webSocketMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("成功建立连接 ---  {}", webSocketSession.getId());
        if (!webSocketMap.containsKey(webSocketSession.getId())) {
            webSocketMap.put(webSocketSession.getId(),webSocketSession);
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("处理消息 ---  {}, {}", webSocketSession.getId(), webSocketMessage.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.info("传输错误 ---  {}, {}", webSocketSession.getId(), throwable.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("关闭连接 ---  {}, {}", webSocketSession.getId(),closeStatus.getCode());
        webSocketMap.remove(webSocketSession.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void pushMessage(String message) {
        webSocketMap.forEach((sId, socketSession) -> {
            try {
                socketSession.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("send message fail : {}" ,e.getMessage());
            }
        });
    }
}
