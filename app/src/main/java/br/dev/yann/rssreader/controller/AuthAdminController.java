package br.dev.yann.rssreader.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.dev.yann.rssreader.annotation.AdminAuthRequired;
import br.dev.yann.rssreader.annotation.AuthRequired;
import br.dev.yann.rssreader.dto.UserDTO;
import br.dev.yann.rssreader.entity.User;
import br.dev.yann.rssreader.model.MessageResponse;
import br.dev.yann.rssreader.service.AuthAdminService;

@AuthRequired
@AdminAuthRequired
@Path("auth/admin")
public class AuthAdminController{


  @Inject
  private MessageResponse messageResponse;

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
  @Path("findByUsername")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findUserByUsernameAsAdmin(@QueryParam("username") String username){
    User user = service.findUserByUsernameAsAdmin(username);
    if(user == null){
      return Response.status(Status.NOT_FOUND).build();
    } else{
      return Response.ok(user).build();
    }
  }

  @GET
  @Path("findById")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findUserByIdAsAdmin(@QueryParam("id") Long id){
    User user = service.findUserByIdAsAdmin(id);
    if(user == null){
      return Response.status(Status.NOT_FOUND).build();
    } else{
      return Response.ok(user).build();
    }

  }

  @PUT
  @Path("update")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response updateUserAsAdmin(@QueryParam("id") Long id, UserDTO.Request.Update user){

     if(user.getUsername() != null && service.hasUsername(user.getUsername(), id)) {
      return Response.status(Status.CONFLICT)
                      .entity(messageResponse.error("Username already exists"))
                      .build();
    }
    user.setId(id);
    service.updateUserAsAdmin(user);
    return Response.ok().build();
  }

  @DELETE
  @Path("delete")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response deleteUserAsAdmin(@QueryParam("id") Long id){
    service.deleteUserAsAdmin(id);
    return Response.ok().build();
  }

}

