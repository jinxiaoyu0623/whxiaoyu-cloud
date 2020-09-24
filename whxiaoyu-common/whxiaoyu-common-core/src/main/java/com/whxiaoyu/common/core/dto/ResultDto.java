package com.whxiaoyu.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.whxiaoyu.common.core.constant.CommonConstants;
import com.whxiaoyu.common.core.enums.ErrorType;
import com.whxiaoyu.common.core.enums.SystemErrorType;
import com.whxiaoyu.common.core.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回结果处理
 * @author jinxiaoyu
 */
@Getter
@Setter
public class ResultDto<T> {

    private String code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResultDto() {}


    public ResultDto(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }

    public ResultDto(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    private ResultDto(String code, String msg, T data) {
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
    public static <T> ResultDto<T> ok(String code,String msg,T data) {
        return new ResultDto<>(code, msg, data);
    }

    /**
     * 成功返回结果
     * @param data 数据对象
     * @return result
     */
    public static <T> ResultDto<T> ok(T data) {
        return ok(CommonConstants.SUCCESS,CommonConstants.SUCCESS_MSG,data);
    }

    /**
     * 快速创建成功结果
     * @return Result
     */
    public static ResultDto<String> ok() {
        return ok("success");
    }

    /**
     * 根据错误类型返回错误信息
     * @param errorType errorType
     * @return result
     */
    public static <T> ResultDto<T> error(ErrorType errorType) {
        return new ResultDto<>(errorType);
    }


    /**
     * 根据自定义异常返回错误信息
     * @param exception ex
     * @return result
     */
    public static <T> ResultDto<T> error(CustomException exception) {
        return new ResultDto<>(exception.getErrorType());
    }

    /**
     * 默认系统异常
     * @return Result
     */
    public static <T> ResultDto<T> error() {
        return error(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 自定义返回异常
     * @param code code
     * @param msg 错误消息
     * @return Result
     */
    public static <T> ResultDto<T> error(String code,String msg) {
        return new ResultDto<>(code,msg,null);
    }



}
