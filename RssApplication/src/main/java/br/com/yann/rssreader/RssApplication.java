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
    JWTToken token = new JWTToken();
    String url = "https://www.fashionlady.in/category/beauty-tips/feed";
   
    RssConvertor convertor = new RssConvertor();
    Rss rss = convertor.getRss(url);
    System.out.println(rss.getLink());
    System.out.println(rss.getTitle());
    System.out.println(rss.getDescription());
  }
}





