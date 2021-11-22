package com.whxiaoyu.component.oss.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.whxiaoyu.component.oss.OssCondition;
import com.whxiaoyu.component.oss.OssProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({OSSClient.class})
@Conditional(OssCondition.class)
public class AliyunOssConfiguration {

    private final OssProperties ossProperties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public OSSClient ossClient() {
        return (OSSClient) new OSSClientBuilder().build(ossProperties.getEndpoint(),ossProperties.getAccessKey(),ossProperties.getAccessKey());
    }

    @Bean
    @ConditionalOnBean(OSSClient.class)
    public AliyunOssProvider aliyunOss() {
        return new AliyunOssProvider(ossClient(),ossProperties);
    }

}
