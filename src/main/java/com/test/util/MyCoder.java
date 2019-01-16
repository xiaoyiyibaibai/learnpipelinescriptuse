package com.test.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

 abstract class Coder {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key).replace("\r", "").replace("\n", "");
    }
}


public class MyCoder extends Coder{
    static class KeyStoreTool{

        /**
         * Java密钥库(Java Key Store，JKS)KEY_STORE
         */
        public static final String KEY_STORE = "JKS";

        public static final String X509 = "X.509";

        /**
         * 获得KeyStore
         *
         * @version   2016-3-16
         * @param keyStorePath
         * @param password
         * @return
         * @throws Exception
         */
        public static KeyStore getKeyStore(String keyStorePath, String password)
                throws Exception {

            try(FileInputStream is = new FileInputStream(new File(keyStorePath))) {
                KeyStore ks = KeyStore.getInstance(KEY_STORE);
                ks.load(is, password.toCharArray());
                is.close();
                return ks;
            }
            catch (Exception e){

            }finally {

            }
       return null;

        }

        /**
         * 由KeyStore获得私钥
         * @param keyStorePath
         * @param alias
         * @param storePass
         * @return
         * @throws Exception
         */
        public static PrivateKey getPrivateKey(String keyStorePath, String alias, String storePass, String keyPass) throws Exception {
            KeyStore ks = getKeyStore(keyStorePath, storePass);
            PrivateKey key = (PrivateKey) ks.getKey(alias, keyPass.toCharArray());
            return key;
        }

        /**
         * 由Certificate获得公钥
         * @param keyStorePath
         *        KeyStore路径
         * @param alias
         *        别名
         * @param storePass
         *        KeyStore访问密码
         * @return
         * @throws Exception
         */
        public static PublicKey getPublicKey(String keyStorePath, String alias, String storePass) throws Exception {
            KeyStore ks = getKeyStore(keyStorePath, storePass);
            PublicKey key = ks.getCertificate(alias).getPublicKey();
            return key;
        }

        /**
         * 从KeyStore中获取公钥，并经BASE64编码
         * @param keyStorePath
         * @param alias
         * @param storePass
         * @return
         * @throws Exception
         */
        public static String getStrPublicKey(String keyStorePath, String alias,String storePass) throws Exception{
            PublicKey key = getPublicKey(keyStorePath, alias, storePass);
            String strKey = Coder.encryptBASE64(key.getEncoded());
            return strKey;
        }

        /*
         * 获取经BASE64编码后的私钥
         * @param keyStorePath
         * @param alias
         * @param storePass
         * @param keyPass
         * @return
         * @throws Exception
         */
        public static String getStrPrivateKey(String keyStorePath, String alias,String storePass, String keyPass) throws Exception{

            PrivateKey key = getPrivateKey(keyStorePath, alias, storePass, keyPass);
            String strKey = Coder.encryptBASE64(key.getEncoded());
            return strKey;
        }

        public static void main(String args[]){
            // 公钥
            String strPublicKey = "";
            // 私钥
            String strPrivateKey = "";

            try {

                strPublicKey =  CertUtil.getPublicKeyString("D:\\cer\\test_pub_cer.cer");
                System.out.println("公钥 = 【" + strPublicKey + "】");

                strPrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAhw9Ma2meOb+o133IVxJGRBcBsIGiSxhZu23vH30RQ8rp3pLitUPGmfdMjeQSkX25QhM7yGIuTeAbj9wXQ2BQhwIDAQABAkArfUqfP8g6UjPdB9yt5O2JWVsq6eNHiRTD3LdmDVC04jZGOUbJdoI59HPgyVCVQAil1KneEFwAe4BmmN50nxshAiEA018GJB0boM267Gj93SCQGNSvCxdAubp2BfBxBR0zX1cCIQCjk4K8f7WWIxwSouz2Ev2XnzKHluN9Uv4oFbp3rfBKUQIhALl99ZyxsrI+eSDIzqhE0FAKLYcVI+cZ3ENAC18Kji/HAiBn6dCWsMaJbxppWGkSVbQVuwNdkWRI/A0LJ+qCFtRcUQIgKcvDUtYMQ/GJ/EUg0NSiwwDFAEBVSFttdjVt/p1zQ0I=";
                System.out.println("\n私钥 = 【" + strPrivateKey + "】");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * 使用公钥加密数据
     * @param publicKey
     * @param srcData
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String publicKey, String srcData) throws Exception{
        //解密
        byte[] pk = Coder.decryptBASE64(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(pk);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        //获取公钥
        PublicKey pubKey = kf.generatePublic(spec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] doFinal = cipher.doFinal(srcData.getBytes());
        return encryptBASE64(doFinal);
    }


    /*
     * 使用私钥解密数据
     * @param privateKey
     * @param data
     * @return
     * @throws Exception
     */
    public static String descryptByPrivateKey(String privateKey, String data) throws Exception{
        // BASE64转码解密私钥
        byte[] pk = Coder.decryptBASE64(privateKey);
        // BASE64转码解密密文
        byte[] text = decryptBASE64(data);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pk);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        // 获取私钥
        PrivateKey prvKey = kf.generatePrivate(spec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prvKey);

        byte[] doFinal = cipher.doFinal(text);
        return new String(doFinal);
    }

    public static void main(){
        // 公钥
        String strPublicKey = "";
        // 私钥
        String strPrivateKey = "";

        try {
            strPublicKey = KeyStoreTool.getStrPublicKey("d://leslie.keystore", "everygold", "123456");
            strPrivateKey = KeyStoreTool.getStrPrivateKey("d://leslie.keystore", "everygold", "123456", "123456");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // 原文
        String originalText = "原文 = 虽然我穷，但是再穷也要去旅游！";
        System.out.println(originalText);

        try {
            // RSA算法 公钥加密随机数
            String secretText = MyCoder.encryptByPublicKey(strPublicKey, originalText);
            System.out.println("\n经RSA公钥加密后 = " + secretText);
            System.out.println("\n经RSA公钥加密后长度 = " + secretText.length());

            String text = MyCoder.descryptByPrivateKey(strPrivateKey, secretText);
            System.out.println("\n经RSA私钥解密后 = 【" + text + "】");
            System.out.println("\n经RSA私钥解密后长度 = 【" + text.length() + "】");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}