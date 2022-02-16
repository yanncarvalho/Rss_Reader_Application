package br.com.yann.rssreader.auth;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTRequestFilter implements ContainerRequestFilter {

  @Inject
  JWTToken tokenJWT;

  //TODO private static final Pattern CHALLENGE_PATTERN = Pattern.compile("^Bearer *([^ ]+) *$", Pattern.CASE_INSENSITIVE);
  private boolean hasToBeAdmin(String path){
      return (path.contains("/admin"));
  }
  private boolean hasToHaveToken(String path){
    return !(path.contains("auth/login")) && !(path.contains("auth/save")) ;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    String path = requestContext.getUriInfo().getAbsolutePath().toString();
    if (hasToHaveToken(path)){
      String jwt = requestContext.getHeaderString("Authorization").substring("Bearer ".length());
      if (jwt == null || jwt.isEmpty())
       requestContext.abortWith(Response.status(406, "AUTHORIZATION NOT ACCEPTED").build());
      Map<String,Object> decode = tokenJWT.decode(jwt);
      if (decode == null){
        requestContext.abortWith(Response.status(401, "TOKEN IS NOT VALID").build());
      }
      if (hasToBeAdmin(path)){
          if (!((Boolean) decode.get("isAdmin"))){
            requestContext.abortWith(Response.status(403, "YOU DON'T HAVE AUTHORIZATION").build());
          }
      }
    }

  }

}

