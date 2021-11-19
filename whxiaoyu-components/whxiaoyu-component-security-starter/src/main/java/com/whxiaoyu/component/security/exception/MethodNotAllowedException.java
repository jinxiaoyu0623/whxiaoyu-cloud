package com.whxiaoyu.component.security.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author jinxiaoyu
 */
public class MethodNotAllowedException extends OAuth2Exception {

    public MethodNotAllowedException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "method_not_allowed";
    }

    @Override
    public int getHttpErrorCode() {
        return 405;
    }

}
