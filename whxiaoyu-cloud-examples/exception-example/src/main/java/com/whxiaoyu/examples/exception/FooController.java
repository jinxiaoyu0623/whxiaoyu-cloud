package com.whxiaoyu.examples.exception;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@RestController
public class FooController {

    @GetMapping("/foo")
    public ResponseResult<String> foo(@RequestParam String name) {
        String foo = null;
        foo.equals(name);
        return ResponseResult.ok();
    }



}
