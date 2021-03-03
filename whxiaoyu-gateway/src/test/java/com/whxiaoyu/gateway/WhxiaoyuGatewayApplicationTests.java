package com.whxiaoyu.gateway;

import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.uc.entity.SysUser;
import com.whxiaoyu.uc.feign.RemoteUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = WhxiaoyuGatewayApplication.class)
class WhxiaoyuGatewayApplicationTests {

    @Autowired
    private RemoteUserService remoteUserService;

    @Test
    void contextLoads() {
        ResultDto<List<SysUser>> resultDto = remoteUserService.list();
        System.out.println(resultDto);
    }

}
