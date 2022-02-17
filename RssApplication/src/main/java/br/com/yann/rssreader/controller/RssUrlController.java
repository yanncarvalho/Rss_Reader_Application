package br.com.yann.rssreader.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.model.Rss;
import br.com.yann.rssreader.model.MessageResponse;
import br.com.yann.rssreader.service.RssUrlService;
import br.com.yann.rssreader.util.RssConvertor;

@Path("rss/url")
public class RssUrlController {

  @Inject
  private RssUrlService service;

  @GET
  @Path("findAll")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response findAllRssByUser(@HeaderParam("Authorization") String token){
    List<String> rss = service.findAll(token.substring("Bearer ".length()));
    return Response.ok(rss).build();
  }

  @DELETE
  @Path("delete")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response deleteRss(@HeaderParam("Authorization") String token, List<String> rssUrls){
     service.deleteRss(token.substring("Bearer ".length()), rssUrls);
    return Response.ok().build();
  }

  @DELETE
  @Path("deleteAll")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response deleteAllRss(@HeaderParam("Authorization") String token, List<String> rssUrls){
     service.deleteAllRss(token.substring("Bearer ".length()));
    return Response.ok().build();
  }
  @POST
  @Path("add")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response addRss(@HeaderParam("Authorization") String token, List<String> rssUrls){
     service.addRss(token.substring("Bearer ".length()), rssUrls);
    return Response.ok().build();
  }

  @POST
  @Path("hasUrl")
  @Consumes(value = MediaType.APPLICATION_JSON)
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response hasRss(@HeaderParam("Authorization") String token, List<String> rssUrls){
    List<String> rssHad = service.hasRss(token.substring("Bearer ".length()), rssUrls);
    return Response.ok(rssHad).build();
  }

  @GET
  @Path("teste")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response hasRss(){
    // JWTToken token = new JWTToken();
    Rss rss = RssConvertor.get("https://blogs.windows.com/msedgedev/feed/");

    return Response.ok(MessageResponse.addMessage("rss",rss)).build();
    // return response.sendElement("name", "ELEMENT")
    //                .sendElement("token", "token")
    //                .sendElement("rss", rss )
    //                .ok()
    //                .build();
  }
}
