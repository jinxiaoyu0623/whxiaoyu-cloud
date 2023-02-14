package com.whxiaoyu.message.sender.impl;

import com.whxiaoyu.message.entity.BusinessMessageEntity;
import com.whxiaoyu.message.sender.NormalMessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.validation.Valid;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class NormalMessageSenderImpl implements NormalMessageSender {

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendNormalMessage(BusinessMessageEntity messageEntity) {
        Message<String> message = MessageBuilder.withPayload(messageEntity.getBody())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build();
        rocketMQTemplate.sendOneWay(messageEntity.getTopic(), message);
    }

    @Override
    public SendResult sendNormalMessageAndReturn(BusinessMessageEntity messageEntity) {
        Message<String> message = MessageBuilder.withPayload(messageEntity.getBody())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build();
        return rocketMQTemplate.syncSend(messageEntity.getTopic(), message);
    }


    @Override
    public void sendNormalMessageAndCallback(BusinessMessageEntity messageEntity, SendCallback sendCallback) {
        Message<String> message = MessageBuilder.withPayload(messageEntity.getBody())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build();
        rocketMQTemplate.asyncSend(messageEntity.getTopic(),message,sendCallback);
    }

}
