package com.whxiaoyu.cloud.security.annotation;

import com.whxiaoyu.cloud.security.PermitAllUrlProperties;
import com.whxiaoyu.cloud.security.configuration.Oauth2ResourceServerConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用资源服务注解
 * @author jinxiaoyu
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties({PermitAllUrlProperties.class})
@Import({Oauth2ResourceServerConfiguration.class})
public @interface EnableResourceServer {
}
