package com.whxiaoyu.uc;

import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.security.annotation.EnableWhxiaoyuResourceServer;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@EnableWhxiaoyuResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class WhxiaoyuUcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuUcServiceApplication.class, args);
    }

}
