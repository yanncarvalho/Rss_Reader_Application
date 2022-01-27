package br.com.yann.rssreader;

import br.com.yann.rssreader.model.Rss;
import br.com.yann.rssreader.util.RssConvertor;

public class App {
 

   public static void main(String[] args)  {

    String url = "https://www.fashionlady.in/category/beauty-tips/feed";
    
    RssConvertor convertor = new RssConvertor ();
    Rss rss = convertor.getRss(url);
    System.out.println(rss.getLink());
    System.out.println(rss.getTitle());
    System.out.println(rss.getDescription());
  }
}





