package com.whxiaoyu.examples.validation;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
import com.whxiaoyu.cloud.encryption.annotation.Decrypt;
import com.whxiaoyu.cloud.encryption.annotation.Encrypt;
import com.whxiaoyu.cloud.encryption.annotation.Sign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@RestController
public class FooController {

    @GetMapping("/encrypt")
    @Encrypt
    public ResponseResult<String> encrypt(String data) {
        return ResponseResult.ok(data);
    }

    @GetMapping("/decrypt")
    public ResponseResult<String> decrypt(@Decrypt String data) {
        return ResponseResult.ok(data);
    }

    @Sign
    @GetMapping("/sign")
    public ResponseResult<String> sign() {
        return ResponseResult.ok();
    }



}
