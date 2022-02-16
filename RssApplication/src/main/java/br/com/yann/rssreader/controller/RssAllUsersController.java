package br.com.yann.rssreader.controller;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.service.RssAllUserService;

@Path("rss")
public class RssAllUsersController {

/*update, atualizar, adicionar */
  @Inject
  RssAllUserService service;

  @GET
  @Path("findAll")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response findAllRssByUser(@HeaderParam("Authorization") String token){
    Set<String> rss = service.findAll(token.substring("Bearer ".length()));
    return Response.ok(rss).build();
  }

  //FIXME
  @DELETE
  @Path("delete")
  @Produces(value = MediaType.APPLICATION_JSON)
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response deleteRss(@HeaderParam("Authorization") String token, Set<String> rssUrls){
     service.deleteRss(token.substring("Bearer ".length()), rssUrls);
    return Response.ok().build();
  }
}
