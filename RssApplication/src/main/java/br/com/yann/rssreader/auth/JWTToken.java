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
  private final byte[] KEY = "60C5E1934BE6E6775B13FFDC25665C4C3854F045A7C073B0B190509ABA2A679A".getBytes();
  @Named("allUser")
  AllUsersDao dao;

  public String encode (User user) {

    try {
      JWSSigner signer = new MACSigner(this.KEY);//user.getPassword().getBytes());

      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
          .claim("Login", user.getLogin())
          .claim("isAdmin", user.getAdmin())
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
  public Map<?,?> decode (String token) {

      try {

         Map<String, Object> map = JWTParser.parse(token).getJWTClaimsSet().toJSONObject();
         byte[] key = dao.findByLogin((String) map.get("Login"))
                          .getPassword()
                          .getBytes();
        if (isTokenValied(token, this.KEY))
          return map;
      } catch (ParseException | JOSEException e) {
        return null;
      }
      return null;


  }

}
