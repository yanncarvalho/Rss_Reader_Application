package br.com.yann.rssreader.auth;

import java.util.Date;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class JWTToken {

  public String generate (String login, String password) {

    RSAKey rsaJWK;
    try {
      rsaJWK = new RSAKeyGenerator(2048)
          .keyID(password)
          .generate();
      JWSSigner signer = new RSASSASigner(rsaJWK);
      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
          .subject(login)
          .issuer("API RSS READER")
          .expirationTime(new Date(new Date().getTime() + 60 * 1000))
          .build();

      SignedJWT signedJWT = new SignedJWT(
          new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
          claimsSet);

      signedJWT.sign(signer);

      return signedJWT.serialize();
    } catch (JOSEException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }
}
