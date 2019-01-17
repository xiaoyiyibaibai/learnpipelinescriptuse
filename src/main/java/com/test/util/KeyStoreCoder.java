package com.test.util;

import com.test.util.detail.CertUtil;
import com.test.util.detail.KeyStoreTool;
import com.test.util.detail.PrivPubKeyBean;
import com.test.util.detail.RSAEncodeAndDecodeUtil;
import org.springframework.util.Base64Utils;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

/**
 * 根据 keystore拿到公钥和密钥
 */
public class KeyStoreCoder {
    public static void main(String[] args) throws Exception {
        PrivPubKeyBean bean = KeyStoreCoder.getPrivPubKeyBean("D:\\cer\\test1547617660027.keystore","123456","SEC_TEST");
       if (bean!=null){
           //在keystore中拿到公钥信息
           PublicKey publicKey = bean.getPublKey();
           byte[] publicKeyEncoded = publicKey.getEncoded();
           System.out.println("公钥："+""+Base64Utils.encodeToString(publicKeyEncoded));
           //在keystore中拿到私钥信息
           PrivateKey privatekey = bean.getPrivKey();
           byte[] privatekeyEncoded = privatekey.getEncoded();
           System.out.println("私钥："+""+Base64Utils.encodeToString(privatekeyEncoded));
           // 测试根据私钥和公钥进行加密和解密
          String testStr = "我是私钥公钥测试数据";
          System.out.println("加密数据："+testStr);
          byte [] testByte =  testStr.getBytes();
          System.out.println("使用私钥进行加密\n");
          byte [] encryptByPrivateStr =  RSAEncodeAndDecodeUtil.encryptByPrivateKey(testByte,privatekeyEncoded);

          byte [] decryByPublicKey = RSAEncodeAndDecodeUtil.decryptByPublicKey(encryptByPrivateStr,publicKeyEncoded);
          System.out.println("keystore中公钥解密后数据："+new String(decryByPublicKey));

           //从cer中拿到公钥信息
           byte [] publicgetbyCer = CertUtil.getPublicKeyByteArray("D:\\cer\\test_pub_cer.cer");
           byte [] decryByPublicKe2 = RSAEncodeAndDecodeUtil.decryptByPublicKey(encryptByPrivateStr,publicgetbyCer);
           System.out.println("cer中公钥解密后数据："+new String(decryByPublicKe2));


           //私鑰 進行base64加密和base64解密

           String encodebase64 = Base64Utils.encodeToString(privatekeyEncoded);
           System.out.println("進行base64加密：\n"+encodebase64);
           byte [] base64decode = Base64Utils.decode(encodebase64.getBytes());
           System.out.println("使用base64轉碼後的私鑰进行加密\n");
           byte []  encryptByPrivateStr2 = RSAEncodeAndDecodeUtil.encryptByPrivateKey(testByte,base64decode);
           byte [] decryByPublicKey2 = RSAEncodeAndDecodeUtil.decryptByPublicKey(encryptByPrivateStr2,publicKeyEncoded);
           System.out.println("keystore中公钥解密后数据："+new String(decryByPublicKey2));
       }
    }

    /***
     *
     * @param keyStorePath
     * @param password
     * @param alias
     * @return
     * @throws Exception
     */
    public static PrivPubKeyBean getPrivPubKeyBean(String keyStorePath,String password,String alias) throws Exception{
        PrivPubKeyBean privPubKeyBean=new PrivPubKeyBean();
        // 获得密钥库
        KeyStore ks = KeyStoreTool.getKeyStore(keyStorePath, password);
        // 获得私钥
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        privPubKeyBean.setPrivKey(privateKey);

        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) ks.getCertificate(alias);
        PublicKey pubKey=x509Certificate.getPublicKey();
        privPubKeyBean.setPublKey(pubKey);
        privPubKeyBean.setSigAlgName(x509Certificate.getSigAlgName());
        return privPubKeyBean;
    }


}