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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.core.enums.AuthErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 客户端异常处理
 * 1. 可以根据 AuthenticationException 不同细化异常处理
 * @author jinxiaoyu
 */
@Component
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		PrintWriter printWriter = response.getWriter();
		ResultDto result;
		if (authException.getCause() instanceof OAuth2Exception) {
			result = ResultDto.error(String.valueOf(HttpStatus.UNAUTHORIZED.value()),((OAuth2Exception) authException.getCause()).getOAuth2ErrorCode());
		} else {
			result = ResultDto.error(AuthErrorType.INSUFFICIENT_SCOPE);
		}
		printWriter.append(objectMapper.writeValueAsString(result));
	}
}
