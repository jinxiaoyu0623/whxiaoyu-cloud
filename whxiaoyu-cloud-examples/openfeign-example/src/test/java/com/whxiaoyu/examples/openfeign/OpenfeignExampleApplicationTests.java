package com.whxiaoyu.examples.openfeign;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenfeignExampleApplicationTests {

    @Autowired
    private TestFeign testFeign;

    @Test
    void contextLoads() {
        ResponseResult<String> result = testFeign.test();
        System.out.println(result.getMsg());
    }

}
