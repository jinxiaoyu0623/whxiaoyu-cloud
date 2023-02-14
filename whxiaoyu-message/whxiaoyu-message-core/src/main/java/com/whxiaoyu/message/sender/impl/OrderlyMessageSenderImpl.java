package com.whxiaoyu.message.sender.impl;

import com.whxiaoyu.message.entity.BusinessMessageEntity;
import com.whxiaoyu.message.sender.OrderLyMessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class OrderlyMessageSenderImpl implements OrderLyMessageSender {

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public SendResult seedOrderlyMessage(BusinessMessageEntity messageEntity,String hashKey) {
        Message<String> message = MessageBuilder.withPayload(messageEntity.getBody()).build();
        return rocketMQTemplate.syncSendOrderly(messageEntity.getTopic(),message,hashKey);
    }
}
