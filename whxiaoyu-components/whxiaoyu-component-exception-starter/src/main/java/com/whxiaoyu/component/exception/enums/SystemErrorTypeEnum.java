package com.whxiaoyu.component.exception.enums;

import com.whxiaoyu.component.core.ErrorType;
import lombok.Getter;

/**
 * 系统异常枚举
 * @author jinxiaoyu
 */
@Getter
public enum SystemErrorTypeEnum implements ErrorType {

    /**
     * 系统异常
     */
    SYSTEM_ERROR(10000, "系统异常"),

    SYSTEM_BUSY(10001, "系统繁忙,请稍候再试"),

    GATEWAY_CONNECT_TIME_OUT(10002, "网关超时"),

    GATEWAY_NOT_FOUND_SERVICE(10404, "服务未找到"),

    METHOD_NOT_ALLOWED(10405, "不支持该方法"),

    GATEWAY_ERROR(10500, "网关异常"),

    SERVICE_CONNECTION_REFUSED(10503, "服务不可用"),

    ARGUMENT_NOT_VALID(20000, "请求参数校验不通过"),

    JSON_NOT_VALID(20001, "json数据异常"),

    UPLOAD_FILE_SIZE_LIMIT(20002, "上传文件大小超过限制"),

    ARGUMENT_MISS(20002, "缺少参数"),

    DUPLICATE_PRIMARY_KEY(30000,"记录已存在"),

    DATA_NOT_FOUND(40404,"数据不存在");

    /**
     * 错误类型码
     */
    private int code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
