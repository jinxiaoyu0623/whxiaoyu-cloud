package com.whxiaoyu.component.security;

import com.whxiaoyu.component.security.exception.CustomizeWebResponseExceptionTranslator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * 资源服务器配置
 * @author jinxiaoyu
 */
@Slf4j
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private final OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler;
	/**
	 * 默认的配置，对外暴露
	 */
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests()
				.antMatchers("/user/info").access("#oauth2.hasScope('user:read')")
//				.anyRequest().access("@webSecurityExpressions.check(authentication,request)")
				.anyRequest().authenticated()
				.and()
				.csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.authenticationEntryPoint(oAuth2AuthenticationEntryPoint())
				.accessDeniedHandler(oAuth2AccessDeniedHandler())
				.expressionHandler(oAuth2WebSecurityExpressionHandler);
	}

	/**
	 * oauth2登录异常处理
	 */
	@Bean
	public OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint() {
		OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
		authenticationEntryPoint.setExceptionTranslator(new CustomizeWebResponseExceptionTranslator());
		return authenticationEntryPoint;
	}

	/**
	 * oauth2鉴权异常处理
	 */
	@Bean
	public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
		OAuth2AccessDeniedHandler accessDeniedHandler = new OAuth2AccessDeniedHandler();
		accessDeniedHandler.setExceptionTranslator(new CustomizeWebResponseExceptionTranslator());
		return accessDeniedHandler;
	}

}
