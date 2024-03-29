package com.whxiaoyu.component.oss;

import com.whxiaoyu.component.oss.aliyun.AliyunOssConfiguration;
import com.whxiaoyu.component.oss.huawei.HuaweiOssConfiguration;
import com.whxiaoyu.component.oss.minio.MinioOssConfiguration;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author jinxiaoyu
 */
public class OssConfigurations {

    private static final Map<OssType, String> MAPPINGS;

    static {
        Map<OssType, String> mappings = new EnumMap<>(OssType.class);
        mappings.put(OssType.minio, MinioOssConfiguration.class.getName());
        mappings.put(OssType.aliyun, AliyunOssConfiguration.class.getName());
        mappings.put(OssType.huawei, HuaweiOssConfiguration.class.getName());
        MAPPINGS = Collections.unmodifiableMap(mappings);
    }

    private OssConfigurations() {
    }

    static String getConfigurationClass(OssType ossType) {
        String configurationClassName = MAPPINGS.get(ossType);
        Assert.state(configurationClassName != null, () -> "Unknown oss type " + ossType);
        return configurationClassName;
    }

    static OssType getType(String configurationClassName) {
        for (Map.Entry<OssType, String> entry : MAPPINGS.entrySet()) {
            if (entry.getValue().equals(configurationClassName)) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Unknown oss configuration class " + configurationClassName);
    }
}
