package br.com.yann.servlet.rssreader.factory;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class RequestXmlFromHttpFactory implements RequestXmlInterface {
     //TODO improve try-catch
     //TODO refactory tratar url
    //TODO implement o not found
    //TODO nao retorno 200 - https://www.howsweeteats.com/feed treat with erro - https://readwrite.comfeed/
    //carega a pg depois redireciona https://www.deque.com/feed/- 

    final private OkHttpClient client =  new OkHttpClient();

    private String xml = ""; 

     public String getXml(String url) throws IOException  {
  
      Request request = new Request.Builder()
                                    .url(url)
                                    .header("Content-Type", "application/xml")
                                   .build();
       
      Response  response = client.newCall(request).execute();
      this.xml = response.body().string();  
  
      return  this.xml;
      
    }

  

  


  
}

