package br.com.yann.rssreader.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.service.RssContentService;
import br.com.yann.rssreader.util.RssPagination;

@Path("rss")
public class RssContentController {

  @Inject
  private RssContentService service;

  @GET
  @Path("getUserContents")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getContents(@HeaderParam("username") String username, @QueryParam("page")  int page, @QueryParam("size") @DefaultValue("10") int size, @QueryParam ("offset") int offset){
    RssPagination rssPage = service.getUserRssContents(username, page, size, offset);

    return Response.ok(rssPage).build();
  }

  @POST
  @Path("convertToRss")
  @Produces(value = MediaType.APPLICATION_JSON)
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response convertRssUrls(List<String> urls, @QueryParam("page") int page, @QueryParam("size") @DefaultValue("10") int size, @QueryParam ("offset") int offset){
    RssPagination rssPage = service.convertRssUrls(urls, page, size, offset);

    return Response.ok(rssPage).build();
  }

}
