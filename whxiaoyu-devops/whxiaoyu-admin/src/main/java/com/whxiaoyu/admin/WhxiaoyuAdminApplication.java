package com.whxiaoyu.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class WhxiaoyuAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuAdminApplication.class, args);
    }

}
