package com.whxiaoyu.cloud.auth.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jinxiaoyu
 */
@RestController
public class CustomizeLoginController {

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }


}
