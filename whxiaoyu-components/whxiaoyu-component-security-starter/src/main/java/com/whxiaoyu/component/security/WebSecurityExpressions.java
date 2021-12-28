package com.whxiaoyu.component.security;

import org.springframework.security.core.Authentication;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * security el表达式 用于动态验证url
 * @author jinxiaoyu
 */
public class WebSecurityExpressions {


    public boolean check(Authentication authentication, HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestUri = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        Object principal = authentication.getPrincipal();
        return principal instanceof CustomizeUserDetails;
    }

}
