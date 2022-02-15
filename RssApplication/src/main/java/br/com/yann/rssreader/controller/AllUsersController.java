package br.com.yann.rssreader.controller;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.service.AllUsersService;

/* TODO
DELETE USER
UPDATE USER
FINDUSER
*/
//TODO melhorar nome
@Path("auth/")
public class AllUsersController {

  @Inject
  AllUsersService service;

  //FIXME
  @GET
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getUser(User user){
    User userAnswer = service.getUser(user);
    if(user == null)
      return Response.status(404).build();

    return Response.ok(userAnswer).build();
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

     String answer = service.saveNewUser(user);
    return Response.ok(answer).build();
  }



}
