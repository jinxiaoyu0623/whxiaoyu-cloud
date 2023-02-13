package com.whxiaoyu.component.sentinel.configuration;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.SphU;
import com.whxiaoyu.component.sentinel.feign.CustomizeSentinelFeign;
import com.whxiaoyu.component.sentinel.interceptor.FeignRequestInterceptor;
import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author jinxiaoyu
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({SphU.class, Feign.class})
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class SentinelFeignConfiguration {

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "feign.sentinel.enabled",havingValue = "true")
    public Feign.Builder feignSentinelBuilder() {
        return CustomizeSentinelFeign.builder().requestInterceptor(new FeignRequestInterceptor());
    }

}
