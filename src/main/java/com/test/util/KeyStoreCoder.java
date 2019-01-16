package com.test.util;

import com.test.util.detail.KeyStoreTool;
import com.test.util.detail.PrivPubKeyBean;
import org.springframework.util.Base64Utils;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class KeyStoreCoder {
    public static void main(String[] args) throws Exception {
        PrivPubKeyBean bean = KeyStoreCoder.getPrivPubKeyBean("D:\\cer\\test1547609262469.keystore","123456","SEC_TEST");
       if (bean!=null){
           PublicKey publicKey = bean.getPublKey();
           byte[] publicKeyEncoded = publicKey.getEncoded();
           System.out.println("公钥："+""+Base64Utils.encodeToString(publicKeyEncoded));
           System.out.println("公钥："+""+ Base64Util.encryptBASE64(publicKeyEncoded));
           PrivateKey privatekey = bean.getPrivKey();
           byte[] privatekeyEncoded = privatekey.getEncoded();
           System.out.println("私钥："+""+Base64Utils.encodeToString(privatekeyEncoded));
           System.out.println("私钥："+""+Base64Util.encryptBASE64(privatekeyEncoded));

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
        KeyStore ks = KeyStoreTool. getKeyStore(keyStorePath, password);
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