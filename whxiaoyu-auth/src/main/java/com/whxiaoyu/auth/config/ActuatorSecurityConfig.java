package com.whxiaoyu.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * actuator安全配置
 *
 * @author jinxiaoyu
 */
@Configuration
public class ActuatorSecurityConfig {

    @Bean
    @Order(2)
    public SecurityFilterChain actuatorSecurityFilterChain(HttpSecurity http) throws Exception {
        http.mvcMatcher("/actuator/**")
                .authorizeRequests(authorize -> authorize.anyRequest().hasRole("ACTUATOR"))
                .httpBasic();
        UserDetails user = User.withUsername("actuator")
                .password("{noop}1234")
                .roles("ACTUATOR")
                .build();
        http.userDetailsService(new InMemoryUserDetailsManager(user));
        return http.build();
    }

}
