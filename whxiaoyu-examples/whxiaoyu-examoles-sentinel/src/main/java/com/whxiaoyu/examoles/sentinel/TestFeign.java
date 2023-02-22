package com.whxiaoyu.examoles.sentinel;

import com.whxiaoyu.component.core.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jinxiaoyu
 */
@FeignClient("test")
public interface TestFeign {

    @GetMapping("/test")
    ResponseResult<String> test();
}
