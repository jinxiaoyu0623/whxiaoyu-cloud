package com.whxiaoyu.component.exception.handler;


import com.whxiaoyu.component.core.ResponseResult;
import com.whxiaoyu.component.exception.BusinessException;
import com.whxiaoyu.component.exception.enums.SystemErrorTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

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
        return ResponseResult.error(SystemErrorTypeEnum.DUPLICATE_PRIMARY_KEY);
    }

    /**
     * valid校验异常 （表单提交时）
     */
    @ExceptionHandler({BindException.class})
    public ResponseResult<String> exceptionHandler(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        FieldError fieldError = fieldErrors.get(0);
        log.warn("参数绑定异常: {}", fieldError.getDefaultMessage());
        return ResponseResult.error(SystemErrorTypeEnum.ARGUMENT_NOT_VALID.getCode(), fieldError.getDefaultMessage());
    }

    /**
     * valid校验异常（json提交时）
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseResult<String> exceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        log.warn("参数绑定异常: {}", fieldErrors.get(0).getDefaultMessage());
        return ResponseResult.error(SystemErrorTypeEnum.ARGUMENT_NOT_VALID.getCode(), fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * valid校验异常 （方法内参数时）
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseResult<System> exceptionHandler(ConstraintViolationException ex) {
        log.warn("方法内参数异常: {}", ex.getMessage());
        String errMsg = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return ResponseResult.error(SystemErrorTypeEnum.ARGUMENT_NOT_VALID.getCode(),errMsg);
    }

    /**
     * json数据转换异常
     */
    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseResult<System> exceptionHandler(HttpMessageConversionException ex) {
        log.warn("http消息转换异常: {}", ex.getMessage());
        return ResponseResult.error(SystemErrorTypeEnum.JSON_NOT_VALID);
    }

    /**
     * servlet请求时异常
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseResult<String> exceptionHandler(ServletRequestBindingException ex) {
        log.warn("缺少参数: {}",ex.getMessage());
        return ResponseResult.error(SystemErrorTypeEnum.ARGUMENT_MISS);
    }

    /**
     * 请求方法不允许
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult<String> exceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("请求方法不允许: {}",ex.getMessage());
        return ResponseResult.error(SystemErrorTypeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 文件传输异常
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseResult<String> exceptionHandler(MultipartException ex) {
        log.warn("文件大小超过限制: {}",ex.getMessage());
        return ResponseResult.error(SystemErrorTypeEnum.UPLOAD_FILE_SIZE_LIMIT);
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
