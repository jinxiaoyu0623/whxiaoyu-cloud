package com.whxiaoyu.devops.generator.proxy;

import lombok.RequiredArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * cglib代理
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class SubjectCglibProxyFactory<T> {

    private final T target;

    /**
     * 代理对象
     */
    @SuppressWarnings("unchecked")
    public T getProxyInstance() {
        //工具类
        Enhancer en = new Enhancer();
        //设置父类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> method.invoke(target,objects));
        //创建子类对象代理
        return (T) en.create();

    }

}
