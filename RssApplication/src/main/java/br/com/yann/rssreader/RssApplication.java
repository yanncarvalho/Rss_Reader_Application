package br.com.yann.rssreader;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.yann.rssreader.util.RssPagination;

@ApplicationPath("")
public class RssApplication extends Application{

   public static void main(String[] args) {
     List<String> urls = List.of("https://www.fashionlady.in/category/beauty-tips/feed",
     "https://www.robinosborne.co.uk/feed/",
     "https://feeds.feedburner.com/BmwBlog",
     "http://www.glazman.org/weblog/dotclear/index.php?feed/rss2",
     "http://softwareas.com/feed",
     "https://blogs.windows.com/msedgedev/feed");
     int page = 0;
     int size = 10;
     int offset = 0;
    // RssConvertor convertor = new RssConvertor();
    // Rss rss = convertor.getRss(url);
    // System.out.println(rss.getLink());
    // System.out.println(rss.getTitle());
    // System.out.println(rss.getDescription());
    RssPagination pagination = new RssPagination(urls, page, size, offset);
    System.out.println("pagination: " + pagination);

  }
}





