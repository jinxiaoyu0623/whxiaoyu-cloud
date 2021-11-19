package com.whxiaoyu.component.security.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author jinxiaoyu
 */
public class UnauthorizedException extends OAuth2Exception {

    public UnauthorizedException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "unauthorized";
    }

    @Override
    public int getHttpErrorCode() {
        return 401;
    }

}
