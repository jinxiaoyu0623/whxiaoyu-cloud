package com.whxiaoyu.message.produce.controller;

import com.whxiaoyu.component.core.ResponseResult;
import com.whxiaoyu.message.constant.MessageConstants;
import com.whxiaoyu.message.entity.BusinessMessageEntity;
import com.whxiaoyu.message.sender.TransactionMessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
public class TransactionMessageController {

    private final TransactionMessageSender transactionMessageSender;

    @GetMapping("/send/{tranceId}")
    public ResponseResult<SendResult> send(@PathVariable String tranceId, @RequestBody String body) {
        BusinessMessageEntity messageEntity = BusinessMessageEntity.builder().body(body)
                .topic(MessageConstants.TRANSACTION_TOPIC).build();
        return ResponseResult.ok(transactionMessageSender.sendTransactionMessage(messageEntity,tranceId));
    }
}
