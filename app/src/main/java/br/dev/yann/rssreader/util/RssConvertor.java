package br.dev.yann.rssreader.util;

import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import javax.ejb.Stateful;

import br.dev.yann.rssreader.factory.RequestXmlFromHttpFactory;
import br.dev.yann.rssreader.model.Rss;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

@Stateful
//TODO @Asynchronous
public class RssConvertor {

  private static RequestXmlFromHttpFactory factory = new RequestXmlFromHttpFactory();;
  private static JAXBContext context;


  private static String prepareURI(String uri) {
    return uri.replace(" ", "");
  }

  //TODO melhorar isso
  private static String treatXml(String treatable) {
    ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(treatable);
    String utf8EncodedString = StandardCharsets.UTF_8.decode(byteBuffer).toString();

    String treated = utf8EncodedString.replaceAll("\uFEFF", "").replaceAll("\\R+", "").replaceAll("\"xmlns","\" xmlns");

    return treated;

  }

  public static Rss get(String url)  {
      try {
        context = JAXBContext.newInstance(Rss.class);
        String treatable = prepareURI(url);
        String treated = treatXml(factory.getXml(treatable));
        Rss rss  = (Rss) context.createUnmarshaller().unmarshal(new StringReader(treated));
        rss.setOriginalLink(url);
       return rss;
      } catch (JAXBException | IOException e) {
        e.printStackTrace();
        return null;
      }

  }
}



