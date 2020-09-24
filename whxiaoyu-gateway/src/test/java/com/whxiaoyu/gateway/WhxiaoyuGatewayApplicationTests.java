package com.whxiaoyu.gateway;

import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.uc.feign.RemoteUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhxiaoyuGatewayApplicationTests {

    @Autowired
    private RemoteUserService remoteUserService;

    @Test
    void contextLoads() {
        ResultDto resultDto = remoteUserService.list();
        System.out.println(resultDto);
    }

}
