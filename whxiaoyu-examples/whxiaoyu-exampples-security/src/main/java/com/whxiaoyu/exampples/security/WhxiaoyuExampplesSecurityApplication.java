package com.whxiaoyu.exampples.security;

import com.whxiaoyu.component.security.annotation.EnableOauth2Client;
import com.whxiaoyu.component.security.annotation.EnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFoo
// @EnableOauth2Client和@EnableResourceServer不可一起使用
//@EnableOauth2Client
@EnableResourceServer
@SpringBootApplication
public class WhxiaoyuExampplesSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuExampplesSecurityApplication.class, args);
    }

}
