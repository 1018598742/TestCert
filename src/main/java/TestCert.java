import cert.MyCert;
import cert.SignTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class TestCert {

    public static void main(String[] args) {
//        try {
//            new MyCert().mySelfCert();
//        } catch (CertificateException | KeyStoreException | NoSuchAlgorithmException | KeyManagementException | IOException e) {
//            e.printStackTrace();
//        }

        new SignTest().signTest();
    }
}
