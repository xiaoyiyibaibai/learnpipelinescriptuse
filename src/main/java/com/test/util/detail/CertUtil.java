package com.test.util.detail;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * 通过cer拿到公钥
 */
public class CertUtil {

    /**
     * @author God
     * 随便找一个.cer文件读取即可
     */
        /**
         * @return X509Cer对象
         * @throws Exception
         * @author God
         * @cerPath Java读取Cer证书信息
         */
        public static X509Certificate getX509CerCate(String cerPath) throws Exception {
            X509Certificate x509Certificate = null;
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            FileInputStream fileInputStream = new FileInputStream(cerPath);
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
            fileInputStream.close();
            System.out.println("读取Cer证书信息...");
            System.out.println("x509Certificate_SerialNumber_序列号___:" + x509Certificate.getSerialNumber());
            System.out.println("x509Certificate_getIssuerDN_发布方标识名___:" + x509Certificate.getIssuerDN());
            System.out.println("x509Certificate_getSubjectDN_主体标识___:" + x509Certificate.getSubjectDN());
            System.out.println("x509Certificate_getSigAlgOID_证书算法OID字符串___:" + x509Certificate.getSigAlgOID());
            System.out.println("x509Certificate_getNotBefore_证书有效期___:" + x509Certificate.getNotAfter());
            System.out.println("x509Certificate_getSigAlgName_签名算法___:" + x509Certificate.getSigAlgName());
            System.out.println("x509Certificate_getVersion_版本号___:" + x509Certificate.getVersion());
            System.out.println("x509Certificate_getPublicKey_公钥___:" + x509Certificate.getPublicKey());
            PublicKey publicKey = x509Certificate.getPublicKey();
            BASE64Encoder base64Encoder=new BASE64Encoder();
            String publicKeyString = base64Encoder.encode(publicKey.getEncoded());
//            System.out.println("-----------------公钥开始-------------------");
//            System.out.println(publicKeyString);
//            System.out.println("-----------------公钥结束--------------------");
            return x509Certificate;
        }

    public static byte [] getPublicKeyByteArray(String cerPath)throws Exception {
        X509Certificate certificate = getX509CerCate(cerPath);
        PublicKey publicKey = certificate.getPublicKey();
        return publicKey.getEncoded();
    }
        public static String getPublicKeyStringEncodeByBase64(String cerPath)throws Exception {
            byte [] data  = CertUtil.getPublicKeyByteArray(cerPath);
            String publicKeyString = Base64Utils.encodeToString(data);
            System.out.println("-----------------公钥开始-------------------");
            System.out.println(publicKeyString);
            System.out.println("-----------------公钥结束--------------------");
            return publicKeyString;
        }
        public static void main(String[] args) throws Exception {
            getX509CerCate("D:\\test_pub_cer.cer");
        }

    }