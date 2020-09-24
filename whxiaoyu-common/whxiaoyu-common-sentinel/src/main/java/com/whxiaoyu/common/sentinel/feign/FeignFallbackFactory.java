package com.whxiaoyu.common.sentinel.feign;

import feign.Target;
import feign.hystrix.FallbackFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author jinxiaoyu
 * @date 2020/08/17 17:00
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
        enhancer.setCallback(new FeignFallback<>(targetType, targetName, throwable));
        return (T) enhancer.create();
    }
}
