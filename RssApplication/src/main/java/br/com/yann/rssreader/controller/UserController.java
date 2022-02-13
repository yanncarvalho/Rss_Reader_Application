package br.com.yann.rssreader.controller;

import java.util.List;

import javax.inject.Inject;
import javax.print.DocFlavor.URL;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.service.UserService;

@Path("user")
public class UserController {

  @Inject
  UserService service;
  //TODO DTO
  @GET
  @Produces
  public Response getUsers(){
    List<User> users = service.getUsers();
    return Response.ok(users).build();
  }

  @POST
  @Consumes (value = {MediaType.APPLICATION_JSON})
  public Response saveUser(User user){
    service.saveNewUser(user);
    return Response.ok().build();
  }

}
