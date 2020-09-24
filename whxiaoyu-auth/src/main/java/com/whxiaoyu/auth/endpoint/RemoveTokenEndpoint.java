package com.whxiaoyu.auth.endpoint;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 移除token
 * @author jinxiaoyu
 * @date 2019/11/21 11:35
 */
@RequiredArgsConstructor
@RestController
public class RemoveTokenEndpoint {

    private final TokenStore tokenStore;

    @GetMapping("/auth/revoke-token")
    public String revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(authorization)) {
            return "无效的access_token";
        }
        String tokenValue = authorization.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken != null) {
            tokenStore.removeAccessToken(accessToken);
        }
        return "退出成功";
    }

}
