package br.dev.yann.rssreader.filter;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.google.common.primitives.Longs;

import br.dev.yann.rssreader.annotation.AuthRequired;
import br.dev.yann.rssreader.auth.JWTToken;
import br.dev.yann.rssreader.model.MessageResponse;
import br.dev.yann.rssreader.service.AuthAnyUserService;

@Provider
@Priority(100)
@AuthRequired
public class AuthAnyUserFilter implements ContainerRequestFilter {

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private AuthAnyUserService service;

  @Inject
  private JWTToken jwt;
  private final Pattern pattern = Pattern.compile("Bearer\\s\\w*.\\w*.\\w*");

  @Override
  public void filter(ContainerRequestContext request) {

    try {

      String authHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);

      if (authHeader == null) {
        throw new NotAuthorizedException("Token required");
      }

      if (!pattern.matcher(authHeader).find()) {
        throw new NotAuthorizedException("Authentication token not valid");
      }

      String token = authHeader.substring("Bearer ".length());

      Map<String, Object> decode = jwt.decode(token);

      if (decode == null) {
        throw new NotAuthorizedException("Authentication bearer token not valid");
      }

      String idString = Objects.toString(decode.get("sub"));
      Long id = Longs.tryParse(idString);

      if (id == null || !service.hasUserById(id)) {
        throw new NotAuthorizedException("Authentication bearer token not valid");
      }

      request.getHeaders().putSingle("idToken", idString);

    } catch (NotAuthorizedException e) {
      request.abortWith(Response.status(Status.UNAUTHORIZED)
          .entity(messageResponse.error(e.getChallenges().get(0).toString()))
          .build());
    }
  }

}
