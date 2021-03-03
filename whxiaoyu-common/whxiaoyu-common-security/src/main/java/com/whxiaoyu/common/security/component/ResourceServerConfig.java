/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.whxiaoyu.common.security.component;

import com.whxiaoyu.common.security.exception.CustomizeWebResponseExceptionTranslator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

/**
 * 资源服务器配置
 * @author jinxiaoyu
 */
@Slf4j
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private final PermitAllUrlProperties permitAllUrlProperties;


	/**
	 * 默认的配置，对外暴露
	 */
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		//允许使用iframe 嵌套
		httpSecurity.headers().frameOptions().disable();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
				.authorizeRequests();
		permitAllUrlProperties.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
		registry.antMatchers("/sysUser/userInfo").access("#oauth2.hasScope('user')");
		registry.anyRequest().authenticated().and().csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.authenticationEntryPoint(oAuth2AuthenticationEntryPoint())
				.accessDeniedHandler(oAuth2AccessDeniedHandler());
	}

	/**
	 * oauth2登录异常处理
	 */
	private OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint() {
		OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
		authenticationEntryPoint.setExceptionTranslator(new CustomizeWebResponseExceptionTranslator());
		return authenticationEntryPoint;
	}

	/**
	 * oauth2鉴权异常处理
	 */
	private OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
		OAuth2AccessDeniedHandler accessDeniedHandler = new OAuth2AccessDeniedHandler();
		accessDeniedHandler.setExceptionTranslator(new CustomizeWebResponseExceptionTranslator());
		return accessDeniedHandler;
	}

}
