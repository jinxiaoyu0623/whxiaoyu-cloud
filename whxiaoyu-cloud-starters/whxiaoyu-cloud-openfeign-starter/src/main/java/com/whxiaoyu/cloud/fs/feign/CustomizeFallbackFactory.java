package com.whxiaoyu.cloud.fs.feign;

import feign.Target;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * 熔断降级异常回调
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class CustomizeFallbackFactory<T> implements FallbackFactory<T> {

    private final Target<T> target;


    @SuppressWarnings("unchecked")
    @Override
    public T create(Throwable throwable) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new GlobalFeignFallback<>(targetType, targetName, throwable));
        return (T) enhancer.create();
    }
}
