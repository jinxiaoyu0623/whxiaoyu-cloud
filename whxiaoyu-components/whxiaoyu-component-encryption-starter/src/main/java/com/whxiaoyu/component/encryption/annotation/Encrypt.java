package com.whxiaoyu.component.encryption.annotation;

import java.lang.annotation.*;

/**
 * 加密注解
 * @author jinxiaoyu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encrypt {
}
