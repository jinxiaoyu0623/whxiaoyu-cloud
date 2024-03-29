package com.whxiaoyu.cloud.fs.configuration;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.whxiaoyu.cloud.fs.feign.CustomizeSentinelFeign;
import com.whxiaoyu.cloud.fs.interceptor.FeignRequestInterceptor;
import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;

import java.io.PrintWriter;

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
        return CustomizeSentinelFeign.builder();
    }


    /**
     * feign全局拦截器
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }


    /**
     * 流控异常自定义处理
     */
    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return (request, response, ex) -> {
            response.setStatus(429);
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter out = response.getWriter();
            out.print("{\"code\":429,\"msg\":\"系统繁忙。稍后再试\"}");
            out.flush();
            out.close();
        };
    }




}
