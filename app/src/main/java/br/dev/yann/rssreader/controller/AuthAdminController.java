package br.dev.yann.rssreader.controller;

import java.util.List;

import br.dev.yann.rssreader.annotation.AdminAuthRequired;
import br.dev.yann.rssreader.annotation.AuthRequired;
import br.dev.yann.rssreader.entity.User;
import br.dev.yann.rssreader.service.AuthAdminService;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth/admin")
@AuthRequired
@AdminAuthRequired
public class AuthAdminController{

  @Inject
  private AuthAdminService service;

  @GET
  @Path("findAll")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response findAll(){
    List<User> users = service.findAllUsers();
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

