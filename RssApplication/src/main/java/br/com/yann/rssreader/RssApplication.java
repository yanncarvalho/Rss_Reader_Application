package br.com.yann.rssreader;

import java.text.ParseException;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

@ApplicationPath("")
public class RssApplication extends Application{

   public static void main(String[] args) throws KeyLengthException, JOSEException, ParseException   {
    // String url = "https://www.fashionlady.in/category/beauty-tips/feed";



    ;
    // RssConvertor convertor = new RssConvertor();
    // Rss rss = convertor.getRss(url);
    // System.out.println(rss.getLink());
    // System.out.println(rss.getTitle());
    // System.out.println(rss.getDescription());
  }
}





