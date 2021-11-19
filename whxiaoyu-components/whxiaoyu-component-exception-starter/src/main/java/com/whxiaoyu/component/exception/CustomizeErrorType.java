package com.whxiaoyu.component.exception;

import com.whxiaoyu.component.dto.ErrorType;
import lombok.Getter;

/**
 * 自定义错误类型
 * @author jinxiaoyu
 */
public class CustomizeErrorType implements ErrorType {

    @Getter
    private int code;

    @Getter
    private String msg;

    public CustomizeErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
