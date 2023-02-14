package com.whxiaoyu.message.sender.impl;

import com.whxiaoyu.message.entity.BusinessMessageEntity;
import com.whxiaoyu.message.sender.TransactionMessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class TransactionMessageSenderImpl implements TransactionMessageSender {

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public SendResult sendTransactionMessage(BusinessMessageEntity messageEntity,String transactionId) {
       Message<String> msg = MessageBuilder.withPayload(messageEntity.getBody())
               .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
               .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
               .build();
        return rocketMQTemplate.sendMessageInTransaction(messageEntity.getTopic(), msg, transactionId);
    }
}
