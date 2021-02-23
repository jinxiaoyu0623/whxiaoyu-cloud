package com.whxiaoyu.common.sentinel.feign;

import feign.Target;
import feign.hystrix.FallbackFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class FeignFallbackFactory<T> implements FallbackFactory<T> {

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
