package br.dev.yann.rssreader.controller;

import java.util.Map;

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
import javax.ws.rs.core.Response.Status;

import br.dev.yann.rssreader.auth.JWTToken;
import br.dev.yann.rssreader.entity.User;
import br.dev.yann.rssreader.model.MessageResponse;
import br.dev.yann.rssreader.service.AuthAnyUserService;


@Path("auth")
public class AuthAnyUserController {

  @Inject
  private JWTToken tokenJWT;

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private AuthAnyUserService service;



  @GET
  @Path("find")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response find(@HeaderParam("username") String username){


    User user = service.find(username);
    if(user == null)
        return Response.status(Status.NOT_FOUND).build();

    return Response.status(Status.OK)
                    .entity(user)
                    .build();
  }

  @POST
  @Path("login")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response login(User user){
    boolean hasUser = service.hasUser(user);


    if(hasUser){


      return Response.status(Status.OK)
                       .entity(user)
                       .build();
    } else {

      return Response.status(Status.NOT_FOUND).build();
    }

  }

  @POST
  @Path("save")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response save(User user){
    if (service.hasUsername(user.getUsername()))
        return Response.status(Status.CONFLICT)
                       .entity(messageResponse.error("Username already exists"))
                       .build();
    service.save(user);
    return Response.status(Status.CREATED).build();
  }

  @DELETE
  @Path("delete")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response delete(@HeaderParam("username") String username){
    service.delete(username);
  return Response.status(Status.OK).build();
  }

  @PUT
  @Path("update")
  @Consumes (value = {MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response updade(@HeaderParam("username") String username, User user){
    User answerUser = service.update(username, user);

  return Response.status(Status.OK)
                 .entity(answerUser)
                 .build();
  }

}
