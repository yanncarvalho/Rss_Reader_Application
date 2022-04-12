package br.dev.yann.rssreader.auth;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;

import org.bouncycastle.util.encoders.Base64;

import br.dev.yann.rssreader.entity.User;

public class JWTToken {

  private final byte[] PRIVATE_KEY = Base64.decode("JDMxJDE2JFp5NzNqNzItOTd5cjNLRm9DN0hXYVQtelRySlZJTEdMekhEVDZWeWxhV0E=");

  public String getHash(User user) {

    try {
      JWSSigner signer = new MACSigner(this.PRIVATE_KEY);

      long tenDaysFromNow = new Date().getTime() + TimeUnit.DAYS.toMillis(10);

      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
          .claim("username", user.getUsername())
          .expirationTime(new Date(tenDaysFromNow))
          .build();

      SignedJWT signedJWT = new SignedJWT(
          new JWSHeader.Builder(JWSAlgorithm.HS256).build(),
          claimsSet);

      signedJWT.sign(signer);

      return signedJWT.serialize();

    } catch (JOSEException e) {
      e.printStackTrace();
      return null;
    }
  }

  private boolean isTokenValid(String token, byte[] key) throws ParseException, JOSEException {
    JWSObject jwsObject = JWSObject.parse(token);
    JWSVerifier verifier = new MACVerifier(key);
    return jwsObject.verify(verifier);
  }

  public Map<String, Object> decode(String token) {

    try {
      JWTClaimsSet claims = JWTParser.parse(token).getJWTClaimsSet();

      if(claims.getExpirationTime().before(new Date())){
        return null;
      }

      if (isTokenValid(token, this.PRIVATE_KEY)) {
        return claims.toJSONObject();
      }

    } catch (ParseException | JOSEException e) {
      return null;
    }
    return null;
  }
}
