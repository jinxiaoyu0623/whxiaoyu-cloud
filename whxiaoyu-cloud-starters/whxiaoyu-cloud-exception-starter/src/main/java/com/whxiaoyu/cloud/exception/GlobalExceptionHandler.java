package com.whxiaoyu.cloud.exception;


import com.whxiaoyu.cloud.commons.core.ResponseResult;
import com.whxiaoyu.cloud.commons.enums.SystemErrorType;
import com.whxiaoyu.cloud.commons.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

/**
 * 统一异常返回
 * @author jinxiaoyu
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 唯一约束性异常
     */
    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseResult<String> exceptionHandler(DuplicateKeyException ex) {
        log.error("违反唯一约束: {}",ex.getMessage());
        return ResponseResult.error(SystemErrorType.DUPLICATE_PRIMARY_KEY);
    }

    /**
     * 缺少参数
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseResult<String> exceptionHandler(MissingPathVariableException ex) {
        log.warn("缺少参数: {}",ex.getVariableName());
        return ResponseResult.error(SystemErrorType.ARGUMENT_MISS.getCode(),"缺少参数:" + ex.getVariableName());
    }

    /**
     * 缺少参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult<String> exceptionHandler(MissingServletRequestParameterException ex) {
        log.warn("缺少参数: {}",ex.getParameterName());
        return ResponseResult.error(SystemErrorType.ARGUMENT_MISS.getCode(),"缺少参数:" + ex.getParameterName());
    }

    /**
     * 缺少参数
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseResult<String> exceptionHandler(MissingRequestHeaderException ex) {
        log.warn("缺少请求头参数: {}",ex.getHeaderName());
        return ResponseResult.error(SystemErrorType.ARGUMENT_MISS.getCode(),"缺少参数:" + ex.getHeaderName());
    }

    /**
     * 请求方法不允许
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult<String> exceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("请求方法不允许: {}",ex.getMessage());
        return ResponseResult.error(SystemErrorType.METHOD_NOT_ALLOWED);
    }

    /**
     * 文件传输异常
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseResult<String> exceptionHandler(MultipartException ex) {
        log.warn("文件大小超过限制: {}",ex.getMessage());
        return ResponseResult.error(SystemErrorType.UPLOAD_FILE_SIZE_LIMIT);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult<String> exceptionHandler(BusinessException ex) {
        log.error("业务异常: {}",ex.getMessage());
        return ResponseResult.error(ex.getErrorType());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> exceptionHandler(Exception ex) {
        log.error("全局异常: {}",ex.getMessage());
        return ResponseResult.error();
    }

}
