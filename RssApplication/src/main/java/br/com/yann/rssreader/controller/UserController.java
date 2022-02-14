package br.com.yann.rssreader.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.service.UserService;

@Path("auth")
@RolesAllowed({"ADMIN","USER"})
public class UserController {

  @Inject
  UserService service;
  //TODO DTO
  //TODO nao pode retornar as senhas
  //TODO criptografar as senhas
  @GET
  @Produces(value = MediaType.APPLICATION_JSON)
  @RolesAllowed("ADMIN")
  public Response getUsers(){
    List<User> users = service.getUsers();
    return Response.ok(users).build();
  }

  @POST
  @Path("save")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @PermitAll
  public Response saveUser(User user){
     service.saveNewUser(user);
    return Response.ok().build();
  }

  //TODO retorno errado

  @POST
  @Path("login")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.TEXT_PLAIN)
  @PermitAll
  public Response login(User user){
    String answer = service.getUser(user);
    if(answer.isEmpty())
      return Response.status(404).build();

    return Response.ok(answer).build();
  }


}
