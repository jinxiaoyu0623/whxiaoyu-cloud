package com.whxiaoyu.message.sender.impl;

import com.whxiaoyu.message.sender.LogMessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class LogMessageSenderImpl implements LogMessageSender {

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendLogMessage(String topic, String log) {
        Message<String> logMessage = MessageBuilder.withPayload(log)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .build();
        rocketMQTemplate.sendOneWay(topic, logMessage);
    }
}
