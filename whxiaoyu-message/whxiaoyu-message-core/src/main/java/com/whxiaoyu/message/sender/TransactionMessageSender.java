package com.whxiaoyu.message.sender;

import com.whxiaoyu.message.entity.BusinessMessageEntity;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author jinxiaoyu
 */
@Validated
public interface TransactionMessageSender {

    /**
     * 发送事务消息
     * @param messageEntity
     * @param transactionId
     * @return
     */
    SendResult sendTransactionMessage(@Valid BusinessMessageEntity messageEntity, @NotBlank String transactionId);
}
