package com.whxiaoyu.exampples.security;

import com.whxiaoyu.component.core.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@RestController
public class TestController {

    @GetMapping("/")
    public ResponseResult<String> index(Authentication authentication) {
        return ResponseResult.ok(authentication.getName());
    }


    @GetMapping("/api")
    public ResponseResult<String> api() {
        return ResponseResult.ok("hello world!");
    }
}
