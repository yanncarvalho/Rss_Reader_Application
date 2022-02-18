package br.com.yann.rssreader.auth;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import br.com.yann.rssreader.model.MessageResponse;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter {

  @Inject
  private JWTToken tokenJWT;

  @Inject
  private MessageResponse messageResponse;

  //TODO FAZER REGEX E COLOCAR LOG
  //TODO A EXCEÇÃO INDO PARA O SERVIDOR?
  //TODO CACHE
  private boolean hasToBeAdmin(String path){
      return (path.startsWith("auth/admin"));
  }
  private boolean hasToHaveToken(String path){
    return !(path.startsWith("auth/login")) && !(path.startsWith("auth/save")) ;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    String uriBase = requestContext.getUriInfo().getBaseUri().toString();
    String path = requestContext.getUriInfo().getAbsolutePath().toString().substring(uriBase.length());

    if (hasToHaveToken(path)){
      String jwt = requestContext.getHeaderString("Authorization").substring("Bearer ".length());
      if (jwt == null || jwt.isEmpty()){
       requestContext.abortWith(Response.status(Status.NETWORK_AUTHENTICATION_REQUIRED)
                                        .entity(messageResponse.error("BEARER TOKEN AUTHENTICATION REQUIRED"))
                                        .build());
      }
      Map<String,Object> decode = tokenJWT.decode(jwt);
      if (decode == null){
        requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                                          .entity(messageResponse.error("BEARER TOKEN AUTHENTICATION NOT VALID"))
                                          .build());

      }
      if (hasToBeAdmin(path)){
          if (!((Boolean) decode.get("isAdmin"))){
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                                              .entity(messageResponse.error("ADMINISTRATOR ROLE REQUIRED"))
                                              .build());
        }
      }
    }

  }

}

