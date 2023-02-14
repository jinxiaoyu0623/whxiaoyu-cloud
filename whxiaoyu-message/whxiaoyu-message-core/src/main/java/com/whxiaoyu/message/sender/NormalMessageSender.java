package com.whxiaoyu.message.sender;

import com.whxiaoyu.message.entity.BusinessMessageEntity;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 普通消息发送
 * @author jinxiaoyu
 */
@Validated
public interface NormalMessageSender {

    /**
     * 发送普通消息不关心发送结果
     * @param messageEntity 消息实体类
     */
    void sendNormalMessage(@Valid BusinessMessageEntity messageEntity);


    /**
     * 发送普通消息并返回
     * @param messageEntity 消息实体类
     */
    SendResult sendNormalMessageAndReturn(@Valid BusinessMessageEntity messageEntity);


    /**
     * 发送普通消息并设置回调函数
     * @param messageEntity 消息实体类
     * @param sendCallback 设置回调函数
     */
    void sendNormalMessageAndCallback(@Valid BusinessMessageEntity messageEntity, SendCallback sendCallback);


}
