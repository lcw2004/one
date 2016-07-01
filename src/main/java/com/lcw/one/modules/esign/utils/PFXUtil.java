package com.lcw.one.modules.esign.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;


public class PFXUtil {
    private String password;
    private KeyStore keyStore;
    private String alias;
    private PrivateKey privateKey;
    private Certificate[] certChain;

    public PFXUtil(byte[] pfxBytes, String password) throws Exception {
        this(new ByteArrayInputStream(pfxBytes), password);
    }

    public PFXUtil(InputStream is, String password) throws Exception {
        this.password = password;

        keyStore = keyStoreInstance(false);
        keyStore.load(is, password.toCharArray());

        alias = keyStore.aliases().nextElement();
        privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        certChain = keyStore.getCertificateChain(alias);
    }


    public Certificate getCertificate() {
        Certificate certificate = null;
        try {
            String alias = keyStore.aliases().nextElement();

            certificate = keyStore.getCertificate(alias);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return certificate;
    }

    public String getCertificateCN() {
        return getCN(((X509Certificate) getCertificate()).getSubjectDN().toString(), "CN");
    }

    public static String getCN(String certInfo, String InfoType) {
        Map map = new HashMap();
        String[] sub = certInfo.split(",");
        for (int i = 0, len = sub.length; i < len; i++) {
            String[] temp = sub[i].split("=");
            map.put(temp[0].trim(), temp[1]);
        }
        return (String) map.get(InfoType);
    }



    /**
     * 获得Certificate
     *
     * @param certificateByte 证书公钥二进制数据
     * @throws Exception
     */
    public static Certificate getCertificate(byte[] certificateByte) throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(certificateByte);
        Certificate certificate = certificateFactory.generateCertificate(in);
        in.close();

        return certificate;
    }

    private KeyStore keyStoreInstance(boolean isReadCertByBouncyCast) throws KeyStoreException, NoSuchProviderException {
        KeyStore keyStore;

         keyStore = KeyStore.getInstance("PKCS12");
        return keyStore;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public String getAlias() {
        return alias;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public Certificate[] getCertChain() {
        return certChain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
