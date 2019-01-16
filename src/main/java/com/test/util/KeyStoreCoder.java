package com.test.util;

import sun.plugin2.util.SystemUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class KeyStoreCoder {
    public static void main(String[] args) throws Exception {
         KeyStoreCoder.getKeyStore("D:\\cer\\test1547609262469.keystore","123456");
    }
    /**
     * Java密钥库(Java Key Store，JKS)KEY_STORE
     */
    public static final String KEY_STORE = "JKS";
    public static class PrivPubKeyBean implements Serializable {

        private static final long serialVersionUID = 1888415926054715509L;
        /***
         * 私钥
         */
        private PrivateKey privKey;
        /***
         * 公钥
         */
        private PublicKey publKey;
        /***
         * 签名算法
         */
        private String sigAlgName;

        public PrivateKey getPrivKey() {
            return privKey;
        }

        public void setPrivKey(PrivateKey privKey) {
            this.privKey = privKey;
        }

        public PublicKey getPublKey() {
            return publKey;
        }

        public void setPublKey(PublicKey publKey) {
            this.publKey = publKey;
        }

        public String getSigAlgName() {
            return sigAlgName;
        }

        public void setSigAlgName(String sigAlgName) {
            this.sigAlgName = sigAlgName;
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
        KeyStore ks = KeyStoreCoder. getKeyStore(keyStorePath, password);
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

}