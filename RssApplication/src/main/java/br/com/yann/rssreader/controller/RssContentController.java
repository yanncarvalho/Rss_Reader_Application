package br.com.yann.rssreader.controller;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.model.Rss;
import br.com.yann.rssreader.service.RssContentService;

@Path("rss")
public class RssContentController {

  @Inject
  private RssContentService service;

  @GET
  @Path("getContents")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getContents(@HeaderParam("Authorization") String token){
    Set<Rss> rssSets = service.getContents(token.substring("Bearer ".length()));
    return Response.ok(rssSets).build();
  }


}
