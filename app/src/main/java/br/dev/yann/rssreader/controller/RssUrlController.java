package br.dev.yann.rssreader.controller;

import java.util.List;

import br.dev.yann.rssreader.annotation.Authorization;
import br.dev.yann.rssreader.service.RssUrlService;
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

@Path("rss/url")
@Authorization
public class RssUrlController {

  @Inject
  private RssUrlService service;

  @GET
  @Path("findAll")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response findAllRssByUser(@HeaderParam("username") String username){
    List<String> rss = service.findAll(username);
    return Response.ok(rss).build();
  }

  @DELETE
  @Path("delete")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response deleteRss(@HeaderParam("username") String username, List<String> rssUrls){
     service.deleteRss(username, rssUrls);
    return Response.ok().build();
  }

  @DELETE
  @Path("deleteAll")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response deleteAllRss(@HeaderParam("username") String username, List<String> rssUrls){
     service.deleteAllRss(username);
    return Response.ok().build();
  }
  @POST
  @Path("add")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response addRss(@HeaderParam("username") String username, List<String> rssUrls){
     service.addRss(username, rssUrls);
    return Response.ok().build();
  }

  @POST
  @Path("hasUrl")
  @Consumes(value = MediaType.APPLICATION_JSON)
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response hasRss(@HeaderParam("username") String username, List<String> rssUrls){
    //TODO MELHORAR O NOME
    List<String> rssHad = service.hasRss(username, rssUrls);
    return Response.ok(rssHad).build();
  }

}
