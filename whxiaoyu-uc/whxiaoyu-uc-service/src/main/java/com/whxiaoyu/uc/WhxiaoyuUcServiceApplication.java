package com.whxiaoyu.uc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class WhxiaoyuUcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuUcServiceApplication.class, args);
    }

}
