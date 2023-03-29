package com.whxiaoyu.examples.security;

import org.springframework.context.annotation.Bean;

/**
 * @author jinxiaoyu
 */
public class FooConfig {

    @Bean
    public Foo foo() {
        return new Foo();
    }
}
