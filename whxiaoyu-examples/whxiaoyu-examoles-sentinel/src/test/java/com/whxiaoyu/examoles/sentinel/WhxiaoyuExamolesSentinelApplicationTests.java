package com.whxiaoyu.examoles.sentinel;

import com.whxiaoyu.component.core.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhxiaoyuExamolesSentinelApplicationTests {

    @Autowired
    private TestFeign testFeign;

    @Test
    void contextLoads() {
        ResponseResult<String> result = testFeign.test();
        System.out.println(result.getMsg());
    }

}
