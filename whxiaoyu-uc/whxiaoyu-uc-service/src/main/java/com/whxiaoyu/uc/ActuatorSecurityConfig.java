package com.whxiaoyu.uc;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Actuator认证配置
 * need add @Order(-100) : before the resource server configuration
 * @author jinxiaoyu
 */
@Order(-100)
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurityConfig {

    @Bean
    public SecurityFilterChain actuatorSecurityChain(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) ->
                requests.anyRequest().authenticated()).httpBasic();
        return http.build();
    }

}
