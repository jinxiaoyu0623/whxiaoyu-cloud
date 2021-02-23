package com.whxiaoyu.common.sentinel.configuration;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.SphU;
import com.whxiaoyu.common.sentinel.feign.CustomizeSentinelFeign;
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
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    public Feign.Builder feignSentinelBuilder() {
        return CustomizeSentinelFeign.builder();
    }

}
