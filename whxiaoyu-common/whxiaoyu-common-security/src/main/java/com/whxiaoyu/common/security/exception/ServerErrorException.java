package com.whxiaoyu.common.security.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author jinxiaoyu
 */
public class ServerErrorException extends OAuth2Exception {

    public ServerErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "server_error";
    }

    @Override
    public int getHttpErrorCode() {
        return 500;
    }

}
