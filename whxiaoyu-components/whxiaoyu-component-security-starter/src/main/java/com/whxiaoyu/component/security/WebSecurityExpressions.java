package com.whxiaoyu.component.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * security el表达式 用于动态验证url
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RefreshScope
@Component
public class WebSecurityExpressions {

    private final PermitAllUrlProperties permitAllUrlProperties;

    public boolean check(Authentication authentication, HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestUri = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        //放行规则
        if (permitAllUrlProperties.getUrls() != null) {
            for (String ignoreUrl : permitAllUrlProperties.getUrls()) {
                if (antPathMatcher.match(ignoreUrl,requestUri)) {
                    return true;
                }
            }
        }

        // 当前角色资源url验证
        return authentication instanceof OAuth2Authentication;

    }

}
