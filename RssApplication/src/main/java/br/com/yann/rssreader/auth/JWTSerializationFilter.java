package br.com.yann.rssreader.auth;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.model.MessageResponse;


@Provider
@Priority(100)
public class JWTSerializationFilter implements ContainerResponseFilter {

  @Inject
  private MessageResponse messageResponse;

  @Inject
  private JWTToken tokenJWT;

  private final Pattern pattern = Pattern.compile("^Bearer\\s\\w*.\\w*.\\w*$");

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
  throws IOException {


      String jwt = requestContext.getHeaderString("Authorization");

      boolean isToken = pattern.matcher(jwt).find();
      String username;
        if(isToken){
          Map<String, Object> decode = tokenJWT.decode(jwt.substring("Bearer ".length()));
          if(Objects.nonNull(decode))
               username = (String) decode.get("username");
          else
              username = null;
          requestContext.getHeaders().add("notValidToken", Boolean.toString(username == null) );
       } else{
          username = null;
          requestContext.getHeaders().add("notValidToken", "false" );
        }

        requestContext.getHeaders().add("Authorization", username);


      if (responseContext.getEntity() instanceof User) {
        responseContext.setEntity(
            messageResponse.addMessage(
                "token", tokenJWT.hash((User) responseContext.getEntity())
                )
            );
      }

  }

}
