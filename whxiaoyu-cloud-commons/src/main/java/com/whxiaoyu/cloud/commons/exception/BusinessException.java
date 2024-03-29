package com.whxiaoyu.cloud.commons.exception;

import com.whxiaoyu.cloud.commons.enums.ErrorType;
import lombok.Getter;

/**
 * 业务异常
 * @author jinxiaoyu
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2289284515082311467L;

    @Getter
    private ErrorType errorType;

    public BusinessException(ErrorType errorType) {
        super(errorType.getMsg());
        this.errorType = errorType;
    }

    public BusinessException(ErrorType errorType, Throwable cause) {
        super(errorType.getMsg(), cause);
        this.errorType = errorType;
    }

}
