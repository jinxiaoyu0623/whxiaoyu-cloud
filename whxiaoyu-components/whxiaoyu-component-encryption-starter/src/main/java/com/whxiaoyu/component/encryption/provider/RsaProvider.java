package com.whxiaoyu.component.encryption.provider;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author jinxiaoyu
 */
@Slf4j
@RequiredArgsConstructor
public class RsaProvider {

    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    private final MessageDigest messageDigest;

    private static final String EMPTY_STR = "";


    /**
     * 加密数据
     * @param data 数据内容
     * @return String
     */
    public String encryptedData(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception ex) {
            log.error("encryptedData error: {}",ex.getMessage());
            return EMPTY_STR;
        }

    }


    /**
     * 解密数据
     * @param encryptedData 加密数据内容
     * @return String
     */
    public String decryptedData(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //url参数+丢失问题
            String data = encryptedData.replaceAll( " ", "+");
            byte[] base64Bytes = Base64.getDecoder().decode(data);
            byte[] decryptedData = cipher.doFinal(base64Bytes);
            return new String(decryptedData,StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("decryptedData error : {}",ex.getMessage());
            return EMPTY_STR;
        }

    }

    /**
     * 生成签名
     * @param data 待签名数据
     * @return 签名
     */
    public String generateSignature(String data) {
        try {
            byte[] keyBytes = data.getBytes(StandardCharsets.UTF_8);
            messageDigest.update(keyBytes);
            byte[] signatureBytes = messageDigest.digest();
            return bytesToHex(signatureBytes);
        } catch (Exception ex) {
            log.error("sign error : {}",ex.getMessage());
            return EMPTY_STR;
        }
    }


    /**
     * 验证签名
     * @param data 待签名数据
     * @param signature 签名数据
     * @return 是否验证通过
     */
    public boolean verifySignature(String data, String signature) {
        String generatedSignature = generateSignature(data);
        return signature.equals(generatedSignature);
    }


    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }



}
