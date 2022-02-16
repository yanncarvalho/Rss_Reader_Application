package br.com.yann.rssreader.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.service.AuthAllUsersService;


@Path("auth")
public class AuthAllUsersController {

  @Inject
  AuthAllUsersService service;

  @GET
  @Path("findUser")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response findUser(@HeaderParam("Authorization") String token){
    User user = service.find(token.substring("Bearer ".length()));
    if(user == null)
        return Response.status(404).build();
    return Response.ok(user).build();
  }

  @POST
  @Path("login")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response login(User user){
    String answer = service.login(user);
    if(answer == null || answer.isEmpty())
      return Response.status(404).build();
    return Response.ok(answer).build();
  }

  @POST
  @Path("save")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response save(User user){
   String answer = service.save(user);
   if (answer == null || answer.isEmpty())
      return Response.status(404).build();
    return Response.ok(answer).build();
  }

  @DELETE
  @Path("delete")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response delete(@HeaderParam("Authorization") String token){
    service.delete(token.substring("Bearer ".length()));
  return Response.ok().build();
  }


  @PUT
  @Path("update")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response updade(@HeaderParam("Authorization") String token, User user){
    String answer = service.update(token.substring("Bearer ".length()), user);
  return Response.ok(answer).build();
  }

}
