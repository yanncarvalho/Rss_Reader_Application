package br.dev.yann.rssreader.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import br.dev.yann.rssreader.annotation.JWTSerialization;
import br.dev.yann.rssreader.auth.JWTToken;
import br.dev.yann.rssreader.entity.User;
import br.dev.yann.rssreader.model.MessageResponse;

@Provider
@JWTSerialization
public class JWTSerializationFilter implements ContainerResponseFilter{

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private JWTToken jwt;

  @Override
  public void filter(ContainerRequestContext request, ContainerResponseContext response)
      throws IOException {
        if (response.getEntity() instanceof User user) {
          String token = jwt.getToken(user);
          response.setEntity(
              messageResponse.addMessage("access_token", token)
                             .addMessage("token type", "Bearer")
                             .addMessage("expires_in", jwt.decode(token).get("exp"))
                           );

        }
  }

}

