package br.dev.yann.rssreader.auth;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import br.dev.yann.rssreader.annotation.AdminAuthorization;
import br.dev.yann.rssreader.model.MessageResponse;
import br.dev.yann.rssreader.service.AuthAdminService;


@Provider
@Priority(200)
@AdminAuthorization
public class AdminAuthorizationFilter implements ContainerRequestFilter {

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private AuthAdminService service;

  @Override
  public void filter(ContainerRequestContext request){

    String username = request.getHeaderString("username");

    if (!service.findAnyUserByUsername(username).isAdmin()) {
      request.abortWith(Response.status(Status.UNAUTHORIZED)
          .entity(messageResponse.error("Administrative privileges required"))
          .build());
    }

  }

}
