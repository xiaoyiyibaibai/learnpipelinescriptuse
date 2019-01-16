package com.test.util.detail;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAEncodeAndDecodeUtil {
    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipherByPKCS8(key, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(data);
    }
    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {

        Cipher cipher = getCipherByPKCS8(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        //实例化密钥工厂
        Cipher cipher = getCipherByX509(key, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(data);
    }



    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipherByX509(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(data);
    }

    private static Cipher getCipherByPKCS8(byte[] key, int decryptMode) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(decryptMode, privateKey);
        return cipher;
    }
    private static Cipher getCipherByX509(byte[] key, int decryptMode) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(decryptMode, pubKey);
        return cipher;
    }
}
