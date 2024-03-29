package com.whxiaoyu.examples.validation;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinxiaoyu
 */
@Validated
@RestController
public class FooController {

    @GetMapping("/foo")
    public ResponseResult<String> foo(@Length(min = 2,message = "至少输入2个字符") String name) {
        return ResponseResult.ok("Hello World : " + name);
    }



}
