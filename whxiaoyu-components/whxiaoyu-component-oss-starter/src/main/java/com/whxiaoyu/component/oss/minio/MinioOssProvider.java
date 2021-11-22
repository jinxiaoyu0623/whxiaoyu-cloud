package com.whxiaoyu.component.oss.minio;

import com.whxiaoyu.component.oss.OssProperties;
import com.whxiaoyu.component.oss.OssProvider;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
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
public class MinioOssProvider implements OssProvider {

    private final MinioClient minioClient;
    private final OssProperties ossProperties;


    @Override
    @SneakyThrows(Exception.class)
    public void upload(String key, File file) {
        minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(ossProperties.getBucketName())
                .filename(file.getAbsolutePath())
                .object(key)
                .build());
    }


    @Override
    @SneakyThrows(Exception.class)
    public void upload(String key, InputStream stream) {
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(ossProperties.getBucketName())
                .object(key)
                //限制10M
                .stream(stream, -1, 10 * 1024 * 1024)
                .build());
    }


    @Override
    @SneakyThrows(Exception.class)
    public void upload(String key, String base64Data) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(base64Data);
        upload(key, new ByteArrayInputStream(bytes));
    }


    @Override
    public String getHostUrl() {
        return ossProperties.getEndpoint() + "/" + ossProperties.getBucketName() + "/";
    }


}
