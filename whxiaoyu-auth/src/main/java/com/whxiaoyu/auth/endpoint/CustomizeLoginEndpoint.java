package com.whxiaoyu.auth.endpoint;

import com.whxiaoyu.component.dto.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jinxiaoyu
 */
@RestController
public class CustomizeLoginEndpoint {

    /**
     * 登录页面
     */
    @GetMapping("/auth/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    @GetMapping("/")
    public ResponseResult<String> index(Authentication authentication) {
        return ResponseResult.ok("welcome for you : " + authentication.getName());
    }
}
