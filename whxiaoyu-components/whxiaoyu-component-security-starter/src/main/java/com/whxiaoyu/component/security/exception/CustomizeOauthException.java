package com.whxiaoyu.component.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.whxiaoyu.component.dto.ResponseResult;
import com.whxiaoyu.component.exception.enums.AuthErrorTypeEnum;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.*;

/**
 * 定制oauthException 设置异常返回
 * @author jinxiaoyu
 */
@JsonSerialize(using = CustomizeOauthExceptionSerializer.class)
public class CustomizeOauthException extends OAuth2Exception {

    private static final long serialVersionUID = 8530674395977307864L;

    @Getter
    private ResponseResult<String> result;

    public CustomizeOauthException(OAuth2Exception ex) {
        super(ex.getSummary(),ex);
        this.result = ResponseResult.error(getAuthErrorInfo(ex));
    }

    private AuthErrorTypeEnum getAuthErrorInfo(OAuth2Exception ex) {
        if (ex instanceof InvalidClientException) {
            return AuthErrorTypeEnum.INVALID_CLIENT;
        } else if (ex instanceof UnauthorizedClientException) {
            return AuthErrorTypeEnum.UNAUTHORIZED_CLIENT;
        } else if (ex instanceof UnsupportedGrantTypeException) {
            return AuthErrorTypeEnum.UNSUPPORTED_GRANT_TYPE;
        } else if (ex instanceof InsufficientScopeException) {
            return AuthErrorTypeEnum.INSUFFICIENT_SCOPE;
        } else if (ex instanceof InvalidScopeException) {
            return AuthErrorTypeEnum.INVALID_SCOPE;
        } else if (ex instanceof InvalidTokenException) {
            return AuthErrorTypeEnum.INVALID_TOKEN;
        } else if (ex instanceof InvalidRequestException) {
            return AuthErrorTypeEnum.INVALID_REQUEST;
        } else if (ex instanceof RedirectMismatchException) {
            return AuthErrorTypeEnum.REDIRECT_URI_MISMATCH;
        } else if (ex instanceof InvalidGrantException) {
            return AuthErrorTypeEnum.INVALID_GRANT;
        } else if (ex instanceof UnsupportedResponseTypeException) {
            return AuthErrorTypeEnum.UNSUPPORTED_RESPONSE_TYPE;
        } else if (ex instanceof MethodNotAllowedException){
            return AuthErrorTypeEnum.METHOD_NOT_ALLOWED;
        } else if (ex instanceof ForbiddenException) {
            return AuthErrorTypeEnum.ACCESS_DENIED;
        } else if (ex instanceof UnauthorizedException){
            return AuthErrorTypeEnum.UNAUTHORIZED;
        } else {
            return AuthErrorTypeEnum.SERVER_ERROR;
        }
    }
}
