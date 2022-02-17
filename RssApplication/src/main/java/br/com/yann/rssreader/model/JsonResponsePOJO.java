package br.com.yann.rssreader.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonResponsePOJO {

  private String errorMessage;
  private String tokenHash;
  private Rss rss;

  public Rss getRss() {
    return rss;
  }

  @JsonProperty("error")
  public String getErrorMessage() {
    return errorMessage;
  }

  @JsonProperty("token")
  public String getTokenHash() {
    return tokenHash;
  }

  public void error (String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setTokenHash(String tokenHash) {
    this.tokenHash = tokenHash;
  }

  public void setRss(Rss rss) {
    this.rss = rss;
  }

}