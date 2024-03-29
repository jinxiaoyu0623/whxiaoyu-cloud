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
    void upload(String key, File file) throws Exception;


    /**
     * 上传流
     *
     * @param key    key
     * @param stream stream
     */
    void upload(String key, InputStream stream) throws Exception;

    /**
     * 上传base64Data
     *
     * @param key        key
     * @param base64Data base64Data
     */
    void upload(String key, String base64Data) throws Exception;


    /**
     * 获取网络前缀地址
     * @return string
     */
    String getHostUrl();


}
