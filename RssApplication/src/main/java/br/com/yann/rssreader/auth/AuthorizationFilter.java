package br.com.yann.rssreader.auth;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.model.MessageResponse;
import br.com.yann.rssreader.service.AuthAnyUserService;

@Provider
@Priority(200)
public class AuthorizationFilter implements ContainerRequestFilter {

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private AuthAnyUserService service;

  private boolean hasToBeAdmin(String path){
      return (path.startsWith("auth/admin"));
  }
  private boolean hasToHaveAuthentication(String path){
    return !(path.startsWith("auth/login")) && !(path.startsWith("auth/save")) ;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    System.out.println("AuthorizationFilter");

    String uriBase = requestContext.getUriInfo().getBaseUri().toString();
    String path = requestContext.getUriInfo().getAbsolutePath().toString().substring(uriBase.length());


    if (hasToHaveAuthentication(path)){

      if (requestContext.getHeaderString("NotValidToken") != null && requestContext.getHeaderString("NotValidToken").equals("true")){
          requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
              .entity(messageResponse.error("Bearer token authentication not valid"))
              .build());
      }

      String username = requestContext.getHeaderString("Authorization");
      if (username == null || username.isBlank()){
        requestContext.abortWith(Response.status(Status.NETWORK_AUTHENTICATION_REQUIRED)
                                         .entity(messageResponse.error("Authentication required"))
                                         .build());
       }


      if (hasToBeAdmin(path)){
        User user = service.find(username);
          if (Objects.nonNull(user) && !user.isAdmin()){
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                                              .entity(messageResponse.error("Administrator role required"))
                                              .build());
        }
      }

    }
  }

}

