package com.whxiaoyu.gateway;

import com.whxiaoyu.common.sentinel.feign.EnableCustomizeFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCustomizeFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WhxiaoyuGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuGatewayApplication.class, args);
    }

}
