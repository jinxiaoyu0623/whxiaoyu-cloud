package com.whxiaoyu.component.security.annotation;

import com.whxiaoyu.component.security.PermitAllUrlProperties;
import com.whxiaoyu.component.security.ResourceServerAutoConfiguration;
import com.whxiaoyu.component.security.SecurityBeanDefinitionRegistrar;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author jinxiaoyu
 * @date 2020/08/18 13:25
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties({PermitAllUrlProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ResourceServerAutoConfiguration.class, SecurityBeanDefinitionRegistrar.class})
public @interface EnableWhxiaoyuResourceServer {
}
