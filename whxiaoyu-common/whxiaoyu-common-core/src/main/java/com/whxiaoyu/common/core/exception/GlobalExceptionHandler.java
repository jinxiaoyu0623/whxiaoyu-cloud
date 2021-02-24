package com.whxiaoyu.common.core.exception;


import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.core.enums.SystemErrorTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.List;

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
    public ResultDto<String> exceptionHandler(DuplicateKeyException ex) {
        log.error(ex.getMessage(),ex);
        return ResultDto.error(SystemErrorTypeEnum.DUPLICATE_PRIMARY_KEY);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler({BindException.class})
    public ResultDto<String> exceptionHandler(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        log.warn("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
        return ResultDto.error(SystemErrorTypeEnum.ARGUMENT_NOT_VALID.getCode(), fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * servlet请求异常
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResultDto<String> exceptionHandler(ServletRequestBindingException ex) {
        return ResultDto.error(SystemErrorTypeEnum.ARGUMENT_MISS);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomizeException.class)
    public ResultDto<String> exceptionHandler(CustomizeException ex) {
        log.error(ex.getMessage(), ex);
        return ResultDto.error(ex);
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public ResultDto<String> exceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResultDto.error();
    }

}
