package com.whxiaoyu.component.security.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author jinxiaoyu
 */
public class ForbiddenException extends OAuth2Exception {

    public ForbiddenException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "access_denied";
    }

    @Override
    public int getHttpErrorCode() {
        return 403;
    }

}
