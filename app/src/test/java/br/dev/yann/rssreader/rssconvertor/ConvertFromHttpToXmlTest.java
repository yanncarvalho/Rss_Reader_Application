package br.dev.yann.rssreader.rssconvertor;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.dev.yann.rssreader.model.Rss;
import br.dev.yann.rssreader.rssconvertor.service.SetOfRssLinks;
import br.dev.yann.rssreader.util.RssConvertor;


public class ConvertFromHttpToXmlTest {

  @Test
  @DisplayName ("itMustConvertMoreThan1kRssLinksAndCheckIfTheMostImportantAtrribuitsInEach One Are Not Null")
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

              Rss rss = RssConvertor.get(url);
              assertNotNull(rss.getTitle());

            } catch (Exception e) {

              fail("Cannot convert: " + url + System.lineSeparator() + e.getMessage());

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
  //}

  // }

}






