package com.whxiaoyu.auth.jose;

import com.nimbusds.jose.jwk.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * @author jinxiaoyu
 */
public class Jwks {

    private static final String KEY_ID = "jinxiaoyu";

    public static RSAKey generateRsa() {
        KeyPair keyPair = KeyGeneratorUtil.generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // @formatter:off
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        // @formatter:on
    }

    public static RSAKey generateLocalRsa() {
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        new ClassPathResource("authorization-server.jks"),
                        "xiaoyu123".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("authorization-server");
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(KEY_ID)
                .keyUse(KeyUse.SIGNATURE)
                .build();
    }

    public static ECKey generateEc() {
        KeyPair keyPair = KeyGeneratorUtil.generateEcKey();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        Curve curve = Curve.forECParameterSpec(publicKey.getParams());
        // @formatter:off
        return new ECKey.Builder(curve, publicKey)
                .privateKey(privateKey)
                .keyID(KEY_ID)
                .build();
        // @formatter:on
    }

    public static OctetSequenceKey generateSecret() {
        SecretKey secretKey = KeyGeneratorUtil.generateSecretKey();
        // @formatter:off
        return new OctetSequenceKey.Builder(secretKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        // @formatter:on
    }
}
