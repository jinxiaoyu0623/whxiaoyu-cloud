package com.whxiaoyu.common.core.exception;

import com.whxiaoyu.common.core.enums.SystemErrorTypeEnum;
import lombok.Getter;

/**
 * 自定义异常
 * @author jinxiaoyu
 */
public class CustomizeException extends RuntimeException {

    private static final long serialVersionUID = 5493647452146539615L;

    @Getter
    private ErrorType errorType = SystemErrorTypeEnum.SYSTEM_ERROR;

    /**
     * 默认是系统异常
     */
    public CustomizeException() {

    }

    public CustomizeException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public CustomizeException(ErrorType errorType, Throwable cause) {
        super(cause);
        this.errorType = errorType;
    }


}
