package br.com.yann.rssreader.auth;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;

public class JWTToken {
  private final String KEY = "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6";

  public String encode (int userHashcode) {


    try {
      RSAKey rsaJWK = new RSAKeyGenerator(2048)
          .keyID	(KEY)
          .generate();
      JWSSigner signer = new RSASSASigner(rsaJWK);
      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
          .subject(Integer.toString(userHashcode))
          .claim("admin", true)
          .expirationTime(new Date(new Date().getTime() + 60 * 1000))
          .build();

      SignedJWT signedJWT = new SignedJWT(
          new JWSHeader.Builder(JWSAlgorithm.RS512).keyID(rsaJWK.getKeyID()).build(),
          claimsSet);

      signedJWT.sign(signer);

      return signedJWT.serialize();
    } catch (JOSEException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }
  public Map<?,?> decode (String token) {
    try {
      JWT jwt = JWTParser.parse(token);

      Map<String, Object> map = jwt.getHeader().toJSONObject();
      map.putAll(jwt.getJWTClaimsSet().toJSONObject());

     return map;
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

   return null;
  }

}
