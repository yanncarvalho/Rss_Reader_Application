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

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter {

  @Inject
  private JWTToken tokenJWT;

//TODO consertar tudp

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
      // responseMessage.setErrorMessage("PROPERLY BEARER TOKEN AUTHENTICATION REQUIRED");
       requestContext.abortWith(Response.status(Status.NETWORK_AUTHENTICATION_REQUIRED)
                                        .entity("responseMessage")
                                        .build());
      }
      Map<String,Object> decode = tokenJWT.decode(jwt);
      if (decode == null){
       // "responseMessage".setErrorMessage("PROPERLY BEARER TOKEN AUTHENTICATION REQUIRED");
        requestContext.abortWith(Response.status(Status.NETWORK_AUTHENTICATION_REQUIRED)
                                          .entity("responseMessage")
                                          .build());

      }
      if (hasToBeAdmin(path)){
          if (!((Boolean) decode.get("isAdmin"))){
           // "responseMessage".setErrorMessage("PROPERLY BEARER TOKEN AUTHENTICATION REQUIRED");
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                                              .entity("responseMessage")
                                              .build());
        }
      }
    }

  }

}

