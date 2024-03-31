package com.whxiaoyu.cloud.auth.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jinxiaoyu
 */
@RestController
public class LoginController {

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    /**
     * 首页
     */
    @GetMapping("/")
    public ModelAndView index(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", authentication.getName());
        return modelAndView;
    }


}
