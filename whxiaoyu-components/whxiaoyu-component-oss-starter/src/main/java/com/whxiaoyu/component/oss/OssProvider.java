package com.whxiaoyu.component.oss;

import lombok.SneakyThrows;

import java.io.File;
import java.io.InputStream;

/**
 * @author jinxiaoyu
 */
public interface OssProvider {


    /**
     * 文件上传
     *
     * @param file file
     * @param key  key
     */
    @SneakyThrows(Exception.class)
    void upload(String key, File file);


    /**
     * 上传流
     *
     * @param key    key
     * @param stream stream
     */
    @SneakyThrows(Exception.class)
    void upload(String key, InputStream stream);

    /**
     * 上传base64Data
     *
     * @param key        key
     * @param base64Data base64Data
     */
    @SneakyThrows(Exception.class)
    void upload(String key, String base64Data);


    /**
     * 获取网络前缀地址
     * @return string
     */
    String getHostUrl();


}
