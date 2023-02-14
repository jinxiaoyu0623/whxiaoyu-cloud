package com.whxiaoyu.devops.generator.proxy;

import lombok.RequiredArgsConstructor;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class StaticProxy implements Subject{

    private final Subject subject;

    @Override
    public void something() {
        subject.something();
    }
}
