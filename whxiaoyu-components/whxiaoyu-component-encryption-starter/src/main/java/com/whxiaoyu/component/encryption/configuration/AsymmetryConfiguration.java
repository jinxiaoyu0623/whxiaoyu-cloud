package com.whxiaoyu.component.encryption.configuration;

import com.whxiaoyu.component.encryption.componet.DecryptArgumentResolver;
import com.whxiaoyu.component.encryption.componet.EncryptResponseBody;
import com.whxiaoyu.component.encryption.componet.SignAspect;
import com.whxiaoyu.component.encryption.provider.RsaProvider;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

/**
 * 非对称性密钥对配置
 * @author jinxiaoyu
 */
@Configuration
public class AsymmetryConfiguration implements WebMvcConfigurer {


    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 初始化密钥工厂
     */
    @Bean
    public KeyFactory keyFactory() throws Exception {
        return KeyFactory.getInstance(ALGORITHM);
    }

    /**
     * 公钥
     */
    @Bean
    public PublicKey publicKey(KeyFactory keyFactory) throws Exception {
        InputStream inputStream = new ClassPathResource("public.key").getInputStream();
        PemReader reader = new PemReader(new InputStreamReader(inputStream));
        PemObject pemObject = reader.readPemObject();
        byte[] pubKeyBytes = pemObject.getContent();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKeyBytes);
        return keyFactory.generatePublic(spec);
    }


    /**
     * 私钥 PKCS#8格式
     */
    @Bean
    public PrivateKey privateKey(KeyFactory keyFactory) throws Exception {
        InputStream inputStream = new ClassPathResource("private.key").getInputStream();
        PemReader reader = new PemReader(new InputStreamReader(inputStream));
        PemObject pemObject = reader.readPemObject();
        byte[] privateKeyBytes = pemObject.getContent();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 消息摘要
     */
    @Bean
    public MessageDigest messageDigest() throws Exception {
        return  MessageDigest.getInstance("SHA-256");
    }


    /**
     * 密码处理器
     */
    @Bean
    public RsaProvider rsaProvider(PublicKey publicKey,PrivateKey privateKey,MessageDigest messageDigest) {
        return new RsaProvider(publicKey,privateKey,messageDigest);
    }


    @Bean
    public EncryptResponseBody encryptResponseBody() {
        return new EncryptResponseBody();
    }

    @Bean
    public DecryptArgumentResolver decryptArgumentResolver() {
        return new DecryptArgumentResolver();
    }


    @Bean
    public SignAspect signAspect() {
        return new SignAspect();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(decryptArgumentResolver());
    }

}
