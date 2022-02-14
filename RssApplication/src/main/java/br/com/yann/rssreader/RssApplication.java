package br.com.yann.rssreader;

import java.text.ParseException;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.model.Rss;
import br.com.yann.rssreader.util.RssConvertor;

@ApplicationPath("")
public class RssApplication extends Application{

   public static void main(String[] args) throws KeyLengthException, JOSEException, ParseException   {
    // String url = "https://www.fashionlady.in/category/beauty-tips/feed";
    // JWTToken token = new JWTToken();
    // token.decode("eyJraWQiOiI3Zi1qJkNLaz1jb056WmMweTdfNG9iTVA_I1RmY1lxJWZjRDBtRHBlblcybmMhbGZHb1p8ZD9mJlJOYkRIVVg2IiwiYWxnIjoiUlM1MTIifQ.eyJzdWIiOiItMjU5Mzk1MTEzIiwiYWRtaW4iOnRydWUsImV4cCI6MTY0NDg1MTY2M30.MQkFjFxJh9Yyc3NxECoal9NrPhLJNJyUI_PclWPTgp9ilKgEPXSqwET4ykLKdZ1WQ7UhpH7TqXMNlbqru7-TRPLwyg6oqVXoBrLuG9xouuCz0GS068E5gP06yhUCdXSEFgv5ZN3N25NSxkBvf6T00t-h2zN5WTftOKEYMtEXHi-5h3kfFIJSNckuRJszH7xcOhPmNkph_-aZxT4fSFaherhGonIrGfAekAZwAO6mjUMynh85CSZux3D7w8ZJYGajqmXPWll_jLNj7qKNYeu-SE9bFLvMfaTCZix1Dz_4IQ7xGos4oYDK-8OuSFO_gY9bEif6R5Hf4yecYxodWUApuQ");
    // RssConvertor convertor = new RssConvertor();
    // Rss rss = convertor.getRss(url);
    // System.out.println(rss.getLink());
    // System.out.println(rss.getTitle());
    // System.out.println(rss.getDescription());
  }
}





