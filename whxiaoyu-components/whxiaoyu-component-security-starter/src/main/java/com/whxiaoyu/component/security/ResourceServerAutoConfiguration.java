package com.whxiaoyu.component.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 *
 * @author jinxiaoyu
 */
@ComponentScan("com.whxiaoyu.common.security")
public class ResourceServerAutoConfiguration {

    /**
     * oauth 安全表达式处理器
     * 解决之后引入的bean是为了解决no bean resolver registered的问题
     * https://github.com/spring-projects/spring-security-oauth/issues/730#issuecomment-219480394
     */
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

}
