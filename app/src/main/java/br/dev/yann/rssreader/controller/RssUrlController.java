package br.dev.yann.rssreader.controller;

import java.util.List;

import br.dev.yann.rssreader.annotation.AuthRequired;
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
@AuthRequired
public class RssUrlController {

  @Inject
  private RssUrlService service;

  @GET
  @Path("findAll")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response findAllRssByUser(@HeaderParam("idToken") Long id){
    List<String> rss = service.findAll(id);
    return Response.ok(rss).build();
  }

  @DELETE
  @Path("delete")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response deleteRss(@HeaderParam("idToken") Long id, List<String> rssUrls){
     service.deleteRss(id, rssUrls);
    return Response.ok().build();
  }

  @DELETE
  @Path("deleteAll")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response deleteAllRss(@HeaderParam("idToken") Long id, List<String> rssUrls){
     service.deleteAllRss(id);
    return Response.ok().build();
  }
  @POST
  @Path("add")
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response addRss(@HeaderParam("idToken") Long id, List<String> rssUrls){
     service.addRss(id, rssUrls);
    return Response.ok().build();
  }

  @POST
  @Path("hasUrl")
  @Consumes(value = MediaType.APPLICATION_JSON)
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response hasUrl(@HeaderParam("idToken") Long id, List<String> rssUrls){
    List<String> rssList = service.getRssList(id, rssUrls);
    return Response.ok(rssList).build();
  }

}
