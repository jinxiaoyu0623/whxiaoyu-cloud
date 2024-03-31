package com.whxiaoyu.examples.security;

import com.whxiaoyu.cloud.security.annotation.EnableOauth2Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableOauth2Client和@EnableResourceServer不可一起使用
@EnableOauth2Client
//@EnableResourceServer
@SpringBootApplication
public class SecurityExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityExampleApplication.class, args);
    }

}
