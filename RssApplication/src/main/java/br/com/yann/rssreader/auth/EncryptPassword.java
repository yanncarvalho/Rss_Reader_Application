// package br.com.yann.rssreader.auth;

// import java.io.IOException;
// import java.security.cert.CertificateEncodingException;
// import java.security.cert.X509Certificate;


// public class EncryptPassword {

  
//   public static byte[] encryptData(byte[] data, X509Certificate encryptionCertificate) {
//     //FIX ME IMPLEMENT CODING AND DECODING KEY
//     byte[] encryptedData = null;
//     if (null != data && null != encryptionCertificate) {
//         CMSEnvelopedDataGenerator cmsEnvelopedDataGenerator
//           = new CMSEnvelopedDataGenerator();

//         JceKeyTransRecipientInfoGenerator jceKey
//           = new JceKeyTransRecipientInfoGenerator(encryptionCertificate);
//         cmsEnvelopedDataGenerator.addRecipientInfoGenerator(transKeyGen);
//         CMSTypedData msg = new CMSProcessableByteArray(data);
//         OutputEncryptor encryptor
//           = new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES128_CBC)
//           .setProvider("BC").build();
//         CMSEnvelopedData cmsEnvelopedData = cmsEnvelopedDataGenerator
//           .generate(msg,encryptor);
//         encryptedData = cmsEnvelopedData.getEncoded();
//     }
//     return encryptedData;
// }
// }
