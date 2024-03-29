package com.whxiaoyu.cloud.security.configuration;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.whxiaoyu.cloud.security.PermitAllUrlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

/**
 * sso客户端配置
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class Oauth2ClientConfiguration {

    private final PermitAllUrlProperties permitAllUrlProperties;

    @Bean
    public SecurityFilterChain oauth2ClientSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests -> {
                    Set<String> ignoreUrls = permitAllUrlProperties.getUrls();
                    if (CollectionUtil.isNotEmpty(ignoreUrls)) {
                        authorizeRequests.mvcMatchers(ArrayUtil.toArray(ignoreUrls,String.class)).permitAll();
                    }
                    authorizeRequests.anyRequest().authenticated();
                })
                .oauth2Login(oauth2Login -> oauth2Login.loginProcessingUrl("/login/code"))
                .oauth2Client();
        return httpSecurity.build();
    }
}
