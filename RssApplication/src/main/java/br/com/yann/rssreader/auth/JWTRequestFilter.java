package br.com.yann.rssreader.auth;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import br.com.yann.rssreader.data.AllUsersDao;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTRequestFilter implements ContainerRequestFilter {

  @Inject
  JWTToken token;

  //private static final Pattern CHALLENGE_PATTERN = Pattern.compile("^Bearer *([^ ]+) *$", Pattern.CASE_INSENSITIVE);
  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    //TODO testar usando regex
   // String jwt = requestContext.getHeaderString("Authorization").substring("Bearer ".length()).trim();


   // var decode = token.decode(jwt);
    Method[] methods = AllUsersDao.class.getMethods();
    for (Method m : methods)
       if(m.isAnnotationPresent(PermitAll.class)){
         System.out.print("-----------------------------------------PERMIT ALL");
       }
  }

    // Matcher matcher = CHALLENGE_PATTERN.matcher(Optional.ofNullable(kwt).orElse(""));
    // if (matcher.matches()){
    //   requestContext.abortWith(Response.status(401).build());
    //     return;
    // }

    /*{
  "kid": "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6",
  "alg": "RS512"
}
{
  "sub": "-259395113",
  "admin": true,
  "exp": 1644851663
} */
  }

