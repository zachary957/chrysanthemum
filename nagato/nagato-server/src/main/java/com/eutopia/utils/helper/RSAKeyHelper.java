package com.eutopia.utils.helper;

import com.eutopia.constant.JWTConstants;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAKeyHelper {

    public static PrivateKey getPrivateKey(byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance(JWTConstants.KEY_INSTANCE);
        return kf.generatePrivate(spec);
    }

    public static PublicKey getPublicKey(byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance(JWTConstants.KEY_INSTANCE);
        return kf.generatePublic(spec);
    }

    public static Map<String, byte[]> generateKey(String rsaSecret) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JWTConstants.KEY_INSTANCE);
        SecureRandom secureRandom = new SecureRandom(rsaSecret.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        Map<String, byte[]> keyMap = new HashMap<>();
        keyMap.put("pub", keyPair.getPublic().getEncoded());
        keyMap.put("pri", keyPair.getPrivate().getEncoded());
        return keyMap;
    }

    public static String toHexString(byte[] bytes) {
        return new BASE64Encoder().encodeBuffer(bytes);
    }

    public static byte[] toBytes(String text) throws IOException {
        return new BASE64Decoder().decodeBuffer(text);
    }
}
