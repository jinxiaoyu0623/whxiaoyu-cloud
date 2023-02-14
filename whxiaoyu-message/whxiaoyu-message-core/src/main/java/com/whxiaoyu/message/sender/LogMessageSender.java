package com.whxiaoyu.message.sender;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * 日志消息发送
 * <p>单向发送 不关心发送结果</p>
 * @author jinxiaoyu
 */
@Validated
public interface LogMessageSender {

    /**
     * 发送系统日志消息
     * @param topic topic
     * @param logMsg 日志对象json字符串
     */
     void sendLogMessage(@NotBlank String topic, String logMsg);

}
