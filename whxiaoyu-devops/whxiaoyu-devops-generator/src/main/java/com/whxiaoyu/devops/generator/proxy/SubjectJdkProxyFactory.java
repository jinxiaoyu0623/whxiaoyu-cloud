package com.whxiaoyu.devops.generator.proxy;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class SubjectJdkProxyFactory<T> {

    private final T target;

    /**
     * 代理对象
     */
    @SuppressWarnings("unchecked")
    public T getProxyInstance() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> method.invoke(target,args)
        );
    }



}
