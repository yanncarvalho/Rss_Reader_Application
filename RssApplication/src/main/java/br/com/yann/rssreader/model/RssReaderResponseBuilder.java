package br.com.yann.rssreader.model;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class RssReaderResponseBuilder {

  private JsonResponsePOJO message = new JsonResponsePOJO();
  private int status;

  public RssReaderResponseBuilder status(Status status) {
    this.status = status.getStatusCode();
    return this;
  }

  public RssReaderResponseBuilder status(int status) {
    this.status = status;
    return this;
  }
  public RssReaderResponseBuilder setRss(Rss rss) {
    message.setRss(rss);
    return this;
  }
  public RssReaderResponseBuilder ok () {
    this.status = 200;
    return this;
  }

  public RssReaderResponseBuilder setTokenHash (String token) {
    message.setTokenHash(token);
    return this;
  }

  public RssReaderResponseBuilder setErrorMessage(String errorMessage) {
    message.setErrorMessage(errorMessage);
    return this;
  }

  public Response build (){
    return Response.status(status).entity(message).build();
  }

}
