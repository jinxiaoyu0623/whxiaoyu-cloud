package com.whxiaoyu.examples.encryption;

import com.whxiaoyu.component.core.ResponseResult;
import com.whxiaoyu.component.encryption.annotation.Decrypt;
import com.whxiaoyu.component.encryption.annotation.Encrypt;
import com.whxiaoyu.component.encryption.annotation.Sign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@RestController
public class TestController {

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
