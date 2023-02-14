package com.whxiaoyu.component.security.annotation;

import com.whxiaoyu.component.security.PermitAllUrlProperties;
import com.whxiaoyu.component.security.configuration.Oauth2ClientConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用oauth2Client注解
 * @author jinxiaoyu
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties({PermitAllUrlProperties.class})
@Import({Oauth2ClientConfiguration.class})
public @interface EnableOauth2Client {
}
