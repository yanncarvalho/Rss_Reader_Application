package br.com.yann.rssreader.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.model.MessageResponse;
import br.com.yann.rssreader.service.AuthAdminService;

//TODO ERROR? @onError
@Path("auth/admin")
public class AuthAdminController{

  @Inject
  private AuthAdminService service;

  @Inject
  private MessageResponse messageResponse;

  @GET
  @Path("findAll")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response findAll(){
    List<User> users = service.findAll();
    return Response.ok(users).build();
  }

  @GET
  @Path("findByUsername/{username}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAnyUserByUserName(@PathParam("username") String username){
    User user = service.findAnyUserByUsername(username);
    return Response.ok(user).build();
  }

  @PUT
  @Path("update/{username}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response updateAnyUser(@PathParam("username") String username, User user){
    service.updateAnyUser(user);
    return Response.ok().build();
  }

  @DELETE
  @Path("delete/{username}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response deleteAnyUser(@PathParam("username") String username){
    service.deleteAnyUser(username);
    return Response.ok().build();
  }

}

