package com.whxiaoyu.component.oss.huawei;

import com.obs.services.ObsClient;
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
 * 华为云存储配置
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@ConditionalOnClass({ObsClient.class})
@Conditional(OssCondition.class)
public class HuaweiOssConfiguration {

    private final OssProperties ossProperties;

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean
    public ObsClient obsClient() {
        return new ObsClient(ossProperties.getAccessKey(),ossProperties.getAccessKey(),ossProperties.getEndpoint());
    }

    @Bean
    @ConditionalOnBean(ObsClient.class)
    public HuaweiOssProvider huaweiOssProvider() {
        return new HuaweiOssProvider(obsClient(),ossProperties);
    }

}
