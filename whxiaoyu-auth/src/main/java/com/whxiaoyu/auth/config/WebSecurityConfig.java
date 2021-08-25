package com.whxiaoyu.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author jinxiaoyu
 */
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class ActuatorWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;

        public ActuatorWebSecurityConfigurationAdapter(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/actuator/**")
                    .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                    .httpBasic();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                    .withUser("auth").password("{noop}1234").roles("BOOT-ADMIN");
        }
    }

    @Configuration
    public static class FormLoginSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                        .loginPage("/auth/login").permitAll()
                        .loginProcessingUrl("/auth/form").permitAll()
                        .failureUrl("/auth/login?error")
                    .and()
                    .logout()
                        .logoutUrl("/auth/logout").permitAll()
                        .logoutSuccessUrl("/auth/login")
                    .and()
                    .csrf()
                        .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                        .disable();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/css/**");

        }

    }

}
