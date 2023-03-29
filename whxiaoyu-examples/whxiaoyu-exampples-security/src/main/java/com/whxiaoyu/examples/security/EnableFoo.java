package com.whxiaoyu.examples.security;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author jinxiaoyu
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import({FooConfig.class})
public @interface EnableFoo {
}
