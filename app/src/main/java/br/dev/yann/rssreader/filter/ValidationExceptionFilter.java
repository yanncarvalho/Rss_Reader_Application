package br.dev.yann.rssreader.filter;


import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.dev.yann.rssreader.model.MessageResponse;


@Provider
public class ValidationExceptionFilter implements ExceptionMapper<ConstraintViolationException> {



  @Override
  public Response toResponse(ConstraintViolationException exception) {
      return Response.status(Status.BAD_REQUEST)
                     .entity(prepareMessage(exception))
                     .build();
  }

  private MessageResponse prepareMessage(ConstraintViolationException exception) {
    Map<String, String> item = new HashMap<>();
      for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
        String[] pathSplit = cv.getPropertyPath().toString().split(".");
        if(pathSplit.length >= 1){
          //TODO not working formatar
          // quando lista de elements ele coloca "convertRssUrls.arg0[3].<list element>"
          // quando o erro esta em um unico elemento ele coloca "login.arg0.password":
          item.put( "error", pathSplit[pathSplit.length-1] +" - "+ cv.getMessage());
        } else{
          item.put(cv.getPropertyPath().toString(), cv.getMessage());
        }

      }

      return new MessageResponse().addMessage("error", (Object) item);
  }
}