package cert;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCert {

    public void myCert() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, IOException {

        String path = "C:\\Users\\fta\\Desktop\\tempSec\\tomcat_114_215_44_230.cer";

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        InputStream inputStream = new FileInputStream(new File(path));

        Certificate ca = cf.generateCertificate(inputStream);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null, null);
        keystore.setCertificateEntry("ca", ca);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keystore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        X509TrustManager x509TrustManager = (X509TrustManager) tmf.getTrustManagers()[0];

        X509Certificate[] peerCertificates = x509TrustManager.getAcceptedIssuers();
        System.out.println("大小：" + peerCertificates.length);
        for (X509Certificate certificate : peerCertificates) {
            Principal subjectDN = certificate.getSubjectDN();//主体名
            System.out.println(subjectDN.toString());
            String sigAlgName = certificate.getSigAlgName();//签名算法
            System.out.println(sigAlgName);
            Date notBefore = certificate.getNotBefore();//有效期从
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(notBefore);
            System.out.println(format);
            Date notAfter = certificate.getNotAfter();//有效期到
            System.out.println(simpleDateFormat.format(notAfter));
            Principal issuerDN = certificate.getIssuerDN();//签发者
            System.out.println(issuerDN.toString());
            System.out.println(issuerDN.getName());
//            X500Principal subjectX500Principal = certificate
//                    .getSubjectX500Principal();
//            String name = subjectX500Principal.getName();
//            System.out.println("签名名字：" + name);
//            String[] split = name.split(",");
//            for (String str : split) {
//                System.out.println("信息：" + str);
//            }
        }
    }

    public void mySelfCert() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, IOException {

        String path = "C:\\Users\\fta\\Desktop\\tempSec\\tomcat_114_215_44_230.cer";
        InputStream inputStream = new FileInputStream(new File(path));

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        X509Certificate certificate = (X509Certificate) cf.generateCertificate(inputStream);

        Principal subjectDN = certificate.getSubjectDN();//主体名
        System.out.println(subjectDN.toString());
        String sigAlgName = certificate.getSigAlgName();//签名算法
        System.out.println(sigAlgName);
        Date notBefore = certificate.getNotBefore();//有效期从
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(notBefore);
        System.out.println(format);
        Date notAfter = certificate.getNotAfter();//有效期到
        System.out.println(simpleDateFormat.format(notAfter));
        Principal issuerDN = certificate.getIssuerDN();//签发者
        System.out.println(issuerDN.toString());
        System.out.println(issuerDN.getName());

//        StringBuilder builder = new StringBuilder();
//        builder.append("-----BEGIN CERTIFICATE-----\n").append("cert").append("\n-----END CERTIFICATE-----");
    }
}
