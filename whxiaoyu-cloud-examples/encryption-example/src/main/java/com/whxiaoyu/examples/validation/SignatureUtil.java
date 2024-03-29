package com.whxiaoyu.examples.validation;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignatureUtil {
    /**
     * 对参数进行签名
     *
     * @param paramMap 参数Map
     * @param secret   签名密钥
     * @return 签名字符串
     */
    public static String sign(Map<String, Object> paramMap, String secret) {
        // 将参数按照字典序排序，并用&拼接起来
        List<String> keyList = new ArrayList<>(paramMap.keySet());
        Collections.sort(keyList);
        StringBuilder sb = new StringBuilder();
        for (String key : keyList) {
            sb.append(key).append(paramMap.get(key));
        }
        sb.append("secret").append(secret);

        // 对拼接后的字符串进行MD5加密
        return DigestUtils.sha256Hex(sb.toString());

    }

    /**
     * 验证签名是否正确
     *
     * @param paramMap    参数Map
     * @param secret      签名密钥
     * @param signature   签名字符串
     * @return 签名是否正确
     */
    public static boolean verify(Map<String, Object> paramMap, String secret, String signature) {
        // 对参数进行签名计算
        String md5Str = sign(paramMap, secret);
        // 比较计算出的签名和传入的签名是否一致
        return md5Str.equalsIgnoreCase(signature);
    }
}

