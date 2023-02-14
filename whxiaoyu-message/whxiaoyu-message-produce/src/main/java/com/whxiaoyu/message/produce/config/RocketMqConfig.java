package com.whxiaoyu.message.produce.config;

import com.whxiaoyu.message.sender.TransactionMessageSender;
import com.whxiaoyu.message.sender.impl.TransactionMessageSenderImpl;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jinxiaoyu
 */
@Configuration
@RequiredArgsConstructor
public class RocketMqConfig {

    private final RocketMQTemplate rocketMQTemplate;

    @Bean
    public TransactionMessageSender transactionMessageSender() {
        return new TransactionMessageSenderImpl(rocketMQTemplate);
    }

}
