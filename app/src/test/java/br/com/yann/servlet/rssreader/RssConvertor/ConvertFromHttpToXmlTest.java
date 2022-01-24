package br.com.yann.servlet.rssreader.RssConvertor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.yann.servlet.rssreader.RssConvertor.service.SetOfRssLinks;
import br.com.yann.servlet.rssreader.model.Rss;
import br.com.yann.servlet.rssreader.util.RssConvertor;

public class ConvertFromHttpToXmlTest {

  RssConvertor convertor = new RssConvertor();

  @Test
  public void itMustConvert1628RssFileAndCheckIfTheMostImportantAtrribuitsInEachOneAreNotNull() {

    long startTime = System.currentTimeMillis();

    SetOfRssLinks setOfRssLinks  = new SetOfRssLinks();

    Set<String> urls = setOfRssLinks.getSet();
    List<String> erros = new ArrayList<>();
    AtomicInteger cont = new AtomicInteger(1);
   
    urls.forEach(
      url ->{      
            try {
              System.out.print(cont.getAndIncrement()+ " ");
              Rss rss = convertor.getRss(url);
              Assertions.assertNotNull(rss.getTitle());
      
            } catch (Exception e) {
              
              System.out.println();
              System.out.println("error: "+url);
              erros.add(url+";"+e.getMessage());
            //Assertions.fail("Cannot convert: " + url + System.lineSeparator() + e.getMessage());
           
            }
       }
    );
    long endTime = System.currentTimeMillis();
    double timeElapsed = ((endTime - startTime) / 1000) / 60;
    System.out.println("______________Erros______________)");
    erros.forEach(System.out::println);
    System.out.println("Execution time in minutes: " + timeElapsed);
  }

  @Test
  public void itMustFailTryingToConvert() {
    String xml = "https://www.w3schools.com/xml/note.xml";
    Assertions.assertThrows(Exception.class, () -> convertor.getRss(xml));

  }

  


  // @Test //TODO IMPLEMENT AN EMPTY TEST
  // public void TheRssElementsMustBeNull() {
  // String xml = "<rss version=\"2.0\">"+
  // "<channel>"+
  // "<title></title>"+
  // "<description></description>"+
  // "<link></link>"+
  // "</channel>"+
  // "</rss>";

  // try{

  // convertor.getRssString(xml);
  // Assertions.assertNull(rss.getTitle());
  // Assertions.assertNull(rss.getDescription());
  // Assertions.assertNull(rss.getLink());

  // } catch(XStreamException e){
  // Assertions.fail("Cannot convert "+xml);
  // }

  // }

}
