package br.com.yann.rssreader.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.util.RssConvertor;

@Path("rss")
public class RssController {
  
   
  String url = "https://www.fashionlady.in/category/beauty-tips/feed";

  @GET
  @Produces(value = MediaType.APPLICATION_XML)
  public Response converterRss(){
    RssConvertor convertor = new RssConvertor();
    return Response.ok(convertor.getRss(url)).build();
  } 
}
