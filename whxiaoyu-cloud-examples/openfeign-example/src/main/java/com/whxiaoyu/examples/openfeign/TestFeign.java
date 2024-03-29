package com.whxiaoyu.examples.openfeign;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
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
