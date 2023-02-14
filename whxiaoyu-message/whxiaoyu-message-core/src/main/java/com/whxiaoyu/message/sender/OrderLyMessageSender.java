package com.whxiaoyu.message.sender;

import com.whxiaoyu.message.entity.BusinessMessageEntity;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 发送有序信息
 * @author jinxiaoyu
 */
@Validated
public interface OrderLyMessageSender {

    /**
     * 发送有序信息
     * @param messageEntity 消息实体类
     * @param hashKey hash值
     * @return result
     */
    SendResult seedOrderlyMessage(@Valid BusinessMessageEntity messageEntity, @NotBlank String hashKey);
}
