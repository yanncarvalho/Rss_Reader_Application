package br.com.yann.rssreader.rssconvertor;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;

import org.junit.Assert;
import org.junit.Test;

import br.com.yann.rssreader.model.Rss;
import br.com.yann.rssreader.rssconvertor.service.SetOfRssLinks;
import br.com.yann.rssreader.util.RssConvertor;


public class ConvertFromHttpToXmlTest {

  RssConvertor convertor = new RssConvertor();

  @Test
  public void itMustConvertMoreThan1kRssLinksAndCheckIfTheMostImportantAtrribuitsInEachOneAreNotNull() {

    long startTime = System.currentTimeMillis();

    SetOfRssLinks setOfRssLinks  = new SetOfRssLinks();

    Set<String> urls = setOfRssLinks.getSet();
    int size = urls.size();
    double percentVariation = (double) 100/size;
    AtomicDouble percentage = new AtomicDouble();
    AtomicInteger counter = new AtomicInteger(1);
    urls.forEach(
      url ->{   
            percentage.addAndGet(percentVariation);
            System.out.println(String.format("%.2f%% (%d/%d) - %s",percentage.get(),  counter.getAndIncrement(), size, url));   
            try {
            
              Rss rss = convertor.getRss(url);
              Assert.assertNotNull(rss.getTitle());

            } catch (Exception e) {
                 
              Assert.fail("Cannot convert: " + url + System.lineSeparator() + e.getMessage());
           
            }

         
       }
    );
    long endTime = System.currentTimeMillis();
    double timeElapsed = ((endTime - startTime) / 1000) / 60;
    System.out.println("Execution time in minutes: " + timeElapsed);
  }

  // @Test
  // public void itMustFailTryingToConvert() {
  //   String xml = "https://www.w3schools.com/xml/note.xml";
  //   Assertions.assertThrows(UnmarshalException.class, () -> convertor.getRss(xml));

  // }

  


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
