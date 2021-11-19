package com.whxiaoyu.component.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一返回结果处理
 *
 * @author jinxiaoyu
 */
@Getter
@Setter
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -7440048446121729329L;

    public static final int DEFAULT_SUCCESS_CODE = 200;
    public static final int DEFAULT_FAIL_CODE = 500;
    public static final String DEFAULT_SUCCESS_MSG = "success";
    public static final String DEFAULT_FAIL_MSG = "系统异常";

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 返回值
     */
    private T data;


    private ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param code 提示码
     * @param msg 提示消息
     * @param data 数据对象
     * @return result
     */
    public static <T> ResponseResult<T> ok(int code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

    /**
     * 成功返回结果
     * @param data 数据对象
     * @return result
     */
    public static <T> ResponseResult<T> ok(T data) {
        return ok(DEFAULT_SUCCESS_CODE,DEFAULT_SUCCESS_MSG,data);
    }

    /**
     * 快速创建成功结果
     * @return Result
     */
    public static ResponseResult<String> ok() {
        return ok("ok");
    }

    /**
     * 根据错误类型返回错误信息
     * @param errorType errorType
     * @return result
     */
    public static <T> ResponseResult<T> error(ErrorType errorType) {
        return error(errorType.getCode(),errorType.getMsg());
    }


    /**
     * 自定义返回异常
     * @param code code
     * @param msg 错误消息
     * @return Result
     */
    public static <T> ResponseResult<T> error(int code, String msg) {
        return new ResponseResult<>(code,msg,null);
    }


    /**
     * 默认系统异常
     * @return Result
     */
    public static <T> ResponseResult<T> error() {
        return error(DEFAULT_FAIL_CODE,DEFAULT_FAIL_MSG);
    }

}
