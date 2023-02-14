package com.whxiaoyu.message.consume;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WhxiaoyuMessageConsumeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuMessageConsumeApplication.class, args);
    }

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<String> messages = rocketMQTemplate.receive(String.class);
        for (String msg : messages) {
            System.out.println("收到消息:" + msg);
        }
    }
}
