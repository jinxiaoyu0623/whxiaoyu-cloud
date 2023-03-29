package com.whxiaoyu.examples.security;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author jinxiaoyu
 */
public class Foo implements InitializingBean {

    public Foo() {
        System.out.println("Foo实例化开始");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行Foo的业务方法");
    }
}
