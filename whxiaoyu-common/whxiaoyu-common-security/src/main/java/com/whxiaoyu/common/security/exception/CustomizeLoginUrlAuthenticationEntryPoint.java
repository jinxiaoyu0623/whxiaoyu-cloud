package com.whxiaoyu.common.security.exception;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 重写login url
 * @author jinxiaoyu
 */
@Slf4j
public class CustomizeLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public CustomizeLoginUrlAuthenticationEntryPoint() {
        super("/auth/login");
    }

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String encodeUrl = null;
        String paramQuery = request.getQueryString();
        try {
            if (StrUtil.isNotBlank(paramQuery)) {
                encodeUrl = URLEncoder.encode(request.getRequestURL() + "?" + request.getQueryString(), "UTF-8" );
            } else {
                encodeUrl = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8" );
            }
        } catch (UnsupportedEncodingException e) {
            log.error("encode url error {}",e.getMessage());
        }
        return StrUtil.isNotBlank(encodeUrl) ? getLoginFormUrl() + "?callback_url=" + encodeUrl : getLoginFormUrl();
    }
}
