package br.com.yann.rssreader.auth;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.inject.Named;

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

import br.com.yann.rssreader.data.AllUsersDao;
import br.com.yann.rssreader.entity.User;

public class JWTToken {
  private final byte[] PRIVATE_KEY = "2020b07bfa0aaa1a6b7f2c51d2f827a7f9672c7c4956bf63cc45d9ca8b75165357c825154336628c32c56fb1d7b6e8c4271fdc20ecfd25e1b4cd0e9e09e1e097".getBytes();
  @Named("allUser")
  AllUsersDao dao;

  public String encode (User user) {

    try {
      JWSSigner signer = new MACSigner(this.PRIVATE_KEY);//user.getPassword().getBytes());

      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
          .claim("login", user.getUsername())
          .claim("isAdmin", user.isAdmin())
          .expirationTime(new Date(new Date().getTime() + 60 * 1000))
          .build();

      SignedJWT signedJWT = new SignedJWT(
          new JWSHeader.Builder(JWSAlgorithm.HS256).build(),
          claimsSet);

      signedJWT.sign(signer);

      return signedJWT.serialize();
    } catch (JOSEException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }
  private boolean isTokenValied (String token, byte[] key) throws ParseException, JOSEException{
    JWSObject jwsObject = JWSObject.parse(token);
    JWSVerifier verifier = new MACVerifier(key);
     return jwsObject.verify(verifier);
  }
  public Map<String, Object> decode (String token) {

      try {

         Map<String, Object> map = JWTParser.parse(token).getJWTClaimsSet().toJSONObject();

        if (isTokenValied(token, this.PRIVATE_KEY))
          return map;
      } catch (ParseException | JOSEException e) {
        return null;
      }
      return null;


  }

}
