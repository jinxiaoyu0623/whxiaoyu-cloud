package com.whxiaoyu.component.lock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

/**
 * 为注解添加spring El支持
 *
 * @author jinxiaoyu
 */
@Getter
@RequiredArgsConstructor
class LockExpressionRootObject {

    private final Method method;

    private final Object[] args;

    private final Object target;

    private final Class<?> targetClass;

}
