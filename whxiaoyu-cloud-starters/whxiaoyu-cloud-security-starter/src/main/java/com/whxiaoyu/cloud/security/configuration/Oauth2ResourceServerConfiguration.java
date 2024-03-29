package com.whxiaoyu.cloud.security.configuration;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.google.gson.Gson;
import com.whxiaoyu.cloud.commons.core.ResponseResult;
import com.whxiaoyu.cloud.commons.enums.AuthErrorType;
import com.whxiaoyu.cloud.security.PermitAllUrlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

/**
 * 资源服务配置
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class Oauth2ResourceServerConfiguration {

    private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
    private final PermitAllUrlProperties permitAllUrlProperties;
    private final Gson gson = new Gson();

    @Bean
    public SecurityFilterChain resourceSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests -> {
                    Set<String> ignoreUrls = permitAllUrlProperties.getUrls();
                    if (CollectionUtil.isNotEmpty(ignoreUrls)) {
                        authorizeRequests.mvcMatchers(ArrayUtil.toArray(ignoreUrls,String.class)).permitAll();
                    }
                    authorizeRequests.anyRequest().authenticated();
                })
                .oauth2ResourceServer(resourceServerConfigurer -> {
                    resourceServerConfigurer.jwt();
                    //allow access_token as parameter
                    DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
                    bearerTokenResolver.setAllowUriQueryParameter(true);
                    resourceServerConfigurer.bearerTokenResolver(bearerTokenResolver);
                    resourceServerConfigurer.authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(gson.toJson(ResponseResult.error(AuthErrorType.UNAUTHORIZED)));
                    });
                    resourceServerConfigurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(HttpStatus.OK.value());
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(gson.toJson(ResponseResult.error(AuthErrorType.ACCESS_DENIED)));
                    });
                });
        return httpSecurity.build();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri(oAuth2ResourceServerProperties.getJwt().getJwkSetUri())
                .build();
    }

}
