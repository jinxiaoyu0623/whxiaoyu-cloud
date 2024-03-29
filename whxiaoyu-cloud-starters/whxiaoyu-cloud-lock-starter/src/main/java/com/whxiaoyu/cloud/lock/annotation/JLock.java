package com.whxiaoyu.cloud.lock.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁的注解
 *
 * @author jinxiaoyu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface JLock {

    /**
     * 过期时间
     */
    int expireTime() default 30;

    /**
     * key值
     */
    String key() default "";


    /**
     * 时间单位 默认秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


    /**
     * 异常消息
     */
    String message() default "请勿重复请求!";

}
