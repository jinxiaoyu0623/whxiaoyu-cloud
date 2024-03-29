package com.whxiaoyu.component.oss.aliyun;

import com.aliyun.oss.OSSClient;
import com.whxiaoyu.component.oss.OssProperties;
import com.whxiaoyu.component.oss.OssProvider;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.InputStream;

/**
 *
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
public class AliyunOssProvider implements OssProvider {

    private final OSSClient ossClient;
    private final OssProperties ossProperties;

    @Override
    public void upload(String key, File file) {

    }

    @Override
    public void upload(String key, InputStream stream) {

    }

    @Override
    public void upload(String key, String base64Data) {

    }

    @Override
    public String getHostUrl() {
        return null;
    }
}
