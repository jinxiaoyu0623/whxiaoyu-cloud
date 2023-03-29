package com.whxiaoyu.examples.encryption;

import com.whxiaoyu.component.core.ResponseResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@EnableFoo
@SpringBootApplication
public class WhxiaoyuExamplesEncryptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhxiaoyuExamplesEncryptionApplication.class, args);
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<String> exception(Exception ex) {
        return ResponseResult.error(500, ex.getMessage());
    }

}
