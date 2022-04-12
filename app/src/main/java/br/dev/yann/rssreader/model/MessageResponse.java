package br.dev.yann.rssreader.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(Include.NON_NULL)
public class MessageResponse {

  @JsonValue
  private Map<String,Object> messageElements = new HashMap<>();

  public MessageResponse addMessage (String name, Object o){
    messageElements.put(name, o);
    return this;
  }

  public MessageResponse error (String error){
    messageElements.put("error", error);
    return this;
  }


}