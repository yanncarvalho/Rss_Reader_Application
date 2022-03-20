package br.com.yann.rssreader.auth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Singleton;

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

import br.com.yann.rssreader.entity.User;
//TODO REFATORAR
//TODO LER SOBRE EXCEPTION
//TODO VER TEMPO DE ACESSO
//TODO VER SE USUARIO EXISTE NO BANCO
@Singleton
public class JWTToken {

  private final byte[] PRIVATE_KEY;
  private final Pattern pattern = Pattern.compile("^--BEGIN CERTIFICATE--[\\s\\S]*--END\\sCERTIFICATE+--$");
  private final String privateKeyPath = "./RssApplication/src/main/resources/privatekey.pem";


  public JWTToken() throws FileNotFoundException, IOException  {

    try (BufferedReader reader = new BufferedReader(new FileReader(privateKeyPath))) {
        String result = reader.lines().collect(Collectors.joining());
        if (pattern.matcher(result).find()){
          PRIVATE_KEY = getPrivateKey(result);
        } else  {
             throw new IllegalArgumentException("privatekey.pem file not with properly content"+System.lineSeparator());
        }
    } //TODO throws FileNotFoundException(file not found), IOException(ffile que nor be open or reader)
  }

  private byte[] getPrivateKey(String result) {
    int firstIndexOfKey = "--BEGIN CERTIFICATE--".length();
    int lastIndexOfKey = result.lastIndexOf("--END CERTIFICATE--");
    return Base64.decode(result.substring(firstIndexOfKey,lastIndexOfKey));
  }

  public String hash (User user) {

    try {
      JWSSigner signer = new MACSigner(this.PRIVATE_KEY);

      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                                                .claim("username", user.getUsername())
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

  private boolean isTokenValid (String token, byte[] key) throws ParseException, JOSEException{
    JWSObject jwsObject = JWSObject.parse(token);
    JWSVerifier verifier = new MACVerifier(key);
     return jwsObject.verify(verifier);
  }

  public Map<String, Object> decode (String token) {

      try {

         Map<String, Object> map = JWTParser.parse(token).getJWTClaimsSet().toJSONObject();

        if (isTokenValid(token, this.PRIVATE_KEY))
          return map;
      } catch (ParseException | JOSEException e) {
        return null;
      }
      return null;


  }

}
