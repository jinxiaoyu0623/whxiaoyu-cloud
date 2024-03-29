package com.whxiaoyu.cloud.fs.feign;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * feign全局降级处理
 * @author jinxiaoyu
 */
@Slf4j
@RequiredArgsConstructor
public class GlobalFeignFallback<T> implements MethodInterceptor {

    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String errorMessage = cause.getMessage();
        log.error("GlobalFeignFallback:[{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName, errorMessage);
        if (ResponseResult.class == method.getReturnType()) {
            return ResponseResult.error(10506,"调用服务异常");
        }
        return null;
    }
}
