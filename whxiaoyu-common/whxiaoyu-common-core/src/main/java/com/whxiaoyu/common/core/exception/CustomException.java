package com.whxiaoyu.common.core.exception;

import com.whxiaoyu.common.core.enums.ErrorType;
import com.whxiaoyu.common.core.enums.SystemErrorType;
import lombok.Getter;

/**
 * 自定义异常
 * @author jinxiaoyu
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 5493647452146539615L;

    @Getter
    private final ErrorType errorType;

    /**
     * 默认是系统异常
     */
    public CustomException() {
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }

    public CustomException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public CustomException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public CustomException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }


}
