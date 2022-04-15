package br.dev.yann.rssreader.filter;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.google.common.primitives.Longs;

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

    List<Long> adminsIds = service.findAllAdminsIds();

    //If there is no admins, the first user registered will be the admin
    if(adminsIds.isEmpty()){
      Long firstId = service.updateAndGetFirstId();
      adminsIds.add(firstId);
    }

    if (!adminsIds.contains(Longs.tryParse(request.getHeaderString("idToken")))) {
      request.abortWith(Response.status(Status.UNAUTHORIZED)
          .entity(messageResponse.error("Administrative privileges required"))
          .build());
    }

  }

}
