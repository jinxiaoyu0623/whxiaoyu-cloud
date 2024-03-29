package com.whxiaoyu.examples.validation;

import com.whxiaoyu.cloud.encryption.provider.RsaProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

@SpringBootTest
class EncryptionExampleApplicationTests {

    @Autowired
    private RsaProvider rsaProvider;


    @Test
    void contextLoads() throws Exception {

        String data = "hell0 world!";


        String encryptedData = rsaProvider.encryptedData(data);
        System.out.println("加密后对数据:" + encryptedData);


        System.out.println("解密后对数据:" + rsaProvider.decryptedData(encryptedData));

    }


    @Test
    void test() {
        String data = "今夏耦";
        String secretKey = "dadklajslajd134";
        String sign = rsaProvider.generateSignature(data);
        System.out.println(sign);

        System.out.println(rsaProvider.verifySignature(data,sign));
    }

    // 使用RSA公钥加密数据
    public byte[] encryptRSA(PublicKey publicKey, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    // 使用RSA私钥解密数据
    public byte[] decryptRSA(PrivateKey privateKey, byte[] encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }

}
