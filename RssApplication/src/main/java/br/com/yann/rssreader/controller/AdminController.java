package br.com.yann.rssreader.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.service.AdminService;
import jakarta.annotation.security.PermitAll;
/* TODO FAZER:
UPDATEANY
DELETE ANY
FINDBYID
*/
@Path("auth/admin")

public class AdminController{

  @Inject
  AdminService service;
  //TODO DTO
  //TODO nao pode retornar as senhas
  //TODO criptografar as senhas
  @GET
  @PermitAll
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response listUsers(){
    List<User> users = service.getUsers();
    return Response.ok(users).build();
  }





}

