package com.whxiaoyu.component.oss.minio;

import com.whxiaoyu.component.oss.OssCondition;
import com.whxiaoyu.component.oss.OssProperties;
import io.minio.MinioClient;
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
@ConditionalOnClass({MinioClient.class})
@Conditional(OssCondition.class)
public class MinioOssConfiguration {

    private final OssProperties ossProperties;

    @Bean
    @ConditionalOnMissingBean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(),ossProperties.getSecretKey())
                .build();
    }

    @Bean
    @ConditionalOnBean(MinioClient.class)
    public MinioOssProvider minioOssProvider() {
        return new MinioOssProvider(minioClient(),ossProperties);
    }

}
