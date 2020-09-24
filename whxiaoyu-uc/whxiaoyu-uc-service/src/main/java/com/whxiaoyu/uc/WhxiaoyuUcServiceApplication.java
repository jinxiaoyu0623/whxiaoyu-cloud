package com.whxiaoyu.uc;

import com.whxiaoyu.common.security.annotation.EnableWhxiaoyuResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableWhxiaoyuResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class WhxiaoyuUcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuUcServiceApplication.class, args);
    }

}
