package com.whxiaoyu.cloud.validation;


import com.whxiaoyu.cloud.commons.core.ResponseResult;
import com.whxiaoyu.cloud.commons.enums.SystemErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
public class ValidationExceptionHandler {

    /**
     * valid校验异常 （表单提交时）
     */
    @ExceptionHandler({BindException.class})
    public ResponseResult<String> exceptionHandler(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        FieldError fieldError = fieldErrors.get(0);
        log.warn("参数绑定异常: {}", fieldError.getDefaultMessage());
        return ResponseResult.error(SystemErrorType.ARGUMENT_NOT_VALID.getCode(), fieldError.getDefaultMessage());
    }

    /**
     * valid校验异常（json提交时）
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseResult<String> exceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        log.warn("参数绑定异常: {}", fieldErrors.get(0).getDefaultMessage());
        return ResponseResult.error(SystemErrorType.ARGUMENT_NOT_VALID.getCode(), fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * valid校验异常 （方法内参数时）
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseResult<System> exceptionHandler(ConstraintViolationException ex) {
        log.warn("方法内参数异常: {}", ex.getMessage());
        String errMsg = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return ResponseResult.error(SystemErrorType.ARGUMENT_NOT_VALID.getCode(),errMsg);
    }


    /**
     * json数据转换异常
     */
    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseResult<System> exceptionHandler(HttpMessageConversionException ex) {
        log.warn("http消息转换异常: {}", ex.getMessage());
        return ResponseResult.error(SystemErrorType.JSON_NOT_VALID);
    }

}
