package com.whxiaoyu.auth.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jinxiaoyu
 * @date 2020/02/08 23:29
 */
@RestController
public class CustomLoginEndpoint {

    /**
     * 认证页面
     */
    @GetMapping("/auth/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
