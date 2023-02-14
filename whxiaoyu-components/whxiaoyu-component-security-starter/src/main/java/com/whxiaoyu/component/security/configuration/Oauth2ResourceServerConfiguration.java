package com.whxiaoyu.component.security.configuration;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.whxiaoyu.component.security.PermitAllUrlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                    //允许access_token作为参数
                    DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
                    bearerTokenResolver.setAllowUriQueryParameter(true);
                    resourceServerConfigurer.bearerTokenResolver(bearerTokenResolver);
                })
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("\"code\":401,\"msg\":\"未登录\"");
                        }));
        return httpSecurity.build();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri(oAuth2ResourceServerProperties.getJwt().getJwkSetUri())
                .build();
    }


}
