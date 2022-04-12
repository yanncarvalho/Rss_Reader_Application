package br.dev.yann.rssreader.auth;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import br.dev.yann.rssreader.annotation.JWTSerialization;
import br.dev.yann.rssreader.entity.User;
import br.dev.yann.rssreader.model.MessageResponse;

@Provider
@JWTSerialization
public class JWTSerializationFilter implements ContainerResponseFilter{

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private JWTToken token;

  @Override
  public void filter(ContainerRequestContext request, ContainerResponseContext response)
      throws IOException {
        if (response.getEntity() instanceof User) {
          String hash = token.getHash((User) response.getEntity());
          response.setEntity(
              messageResponse.addMessage("access_token", hash)
                             .addMessage("token type", "Bearer")
                             .addMessage("expires_in", token.decode(hash).get("exp"))
                           );

        }
  }

}

