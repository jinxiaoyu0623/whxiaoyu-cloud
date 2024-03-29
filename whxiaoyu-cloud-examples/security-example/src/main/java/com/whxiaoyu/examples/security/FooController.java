package com.whxiaoyu.examples.security;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@RestController
public class FooController {

    @GetMapping("/")
    public ResponseResult<String> index(Authentication authentication) {
        return ResponseResult.ok(authentication.getName());
    }


    @GetMapping("/api")
    public ResponseResult<String> api() {
        return ResponseResult.ok("hello world!");
    }
}
