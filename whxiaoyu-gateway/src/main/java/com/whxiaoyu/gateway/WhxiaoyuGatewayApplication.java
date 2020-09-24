package com.whxiaoyu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.whxiaoyu.uc.feign"})
@EnableDiscoveryClient
@SpringBootApplication
public class WhxiaoyuGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuGatewayApplication.class, args);
    }

}
