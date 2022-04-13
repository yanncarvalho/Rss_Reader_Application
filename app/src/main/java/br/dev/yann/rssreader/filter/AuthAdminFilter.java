package br.dev.yann.rssreader.filter;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import br.dev.yann.rssreader.annotation.AdminAuthRequired;
import br.dev.yann.rssreader.model.MessageResponse;
import br.dev.yann.rssreader.service.AuthAdminService;


@Provider
@Priority(200)
@AdminAuthRequired
public class AuthAdminFilter implements ContainerRequestFilter {

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private AuthAdminService service;

  @Override
  public void filter(ContainerRequestContext request){

    List<String> adminsUsernames = service.findAllAdminUsernames();

    if(adminsUsernames.isEmpty()){
      service.updateFirstIdAsAdmin();
    }

    if (!adminsUsernames.contains(request.getHeaderString("username"))) {
      request.abortWith(Response.status(Status.UNAUTHORIZED)
          .entity(messageResponse.error("Administrative privileges required"))
          .build());
    }

  }

}
