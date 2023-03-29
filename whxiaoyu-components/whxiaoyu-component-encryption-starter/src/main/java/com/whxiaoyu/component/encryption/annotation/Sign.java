package com.whxiaoyu.component.encryption.annotation;

import java.lang.annotation.*;

/**
 * 签名注解
 * @author jinxiaoyu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sign {

    String key() default "123456"; // 密钥
}
