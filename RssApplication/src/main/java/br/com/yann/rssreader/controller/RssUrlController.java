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

import br.com.yann.rssreader.service.RssUrlService;

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
    //TODO MELHORAR O NOME
    List<String> rssHad = service.hasRss(token.substring("Bearer ".length()), rssUrls);
    return Response.ok(rssHad).build();
  }

}
