package com.whxiaoyu.component.oss;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jinxiaoyu
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    private OssType type;

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;

}
