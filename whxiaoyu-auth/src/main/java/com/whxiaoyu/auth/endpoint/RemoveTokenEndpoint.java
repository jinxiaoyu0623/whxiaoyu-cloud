package com.whxiaoyu.auth.endpoint;

import com.whxiaoyu.component.dto.ResponseResult;
import com.whxiaoyu.component.exception.enums.AuthErrorTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 移除token
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
public class RemoveTokenEndpoint {

    private final TokenStore tokenStore;

    @RequestMapping(value = "/auth/removeToken",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseResult<String> removeToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(authorization)) {
            return ResponseResult.error(AuthErrorTypeEnum.INVALID_TOKEN);
        }
        String tokenValue = authorization.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken != null) {
            tokenStore.removeAccessToken(accessToken);
        }
        return ResponseResult.ok();
    }

}
