package com.whxiaoyu.component.oss.huawei;

import com.obs.services.ObsClient;
import com.whxiaoyu.component.oss.OssProperties;
import com.whxiaoyu.component.oss.OssProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class HuaweiOssProvider implements OssProvider {

    private final ObsClient obsClient;
    private final OssProperties ossProperties;


    @SneakyThrows(Exception.class)
    @Override
    public void upload(String keyName, File file) {
        obsClient.putObject(ossProperties.getBucketName(), keyName, file);
    }

    @SneakyThrows(Exception.class)
    @Override
    public void upload(String keyName, InputStream inputStream) {
        obsClient.putObject(ossProperties.getBucketName(), keyName, inputStream);
    }

    @SneakyThrows(Exception.class)
    @Override
    public void upload(String key, String base64Data) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(base64Data);
        upload(key, new ByteArrayInputStream(bytes));
    }


    @Override
    public String getHostUrl() {
        return "https://" + ossProperties.getBucketName() + "." + ossProperties.getEndpoint();
    }


}
