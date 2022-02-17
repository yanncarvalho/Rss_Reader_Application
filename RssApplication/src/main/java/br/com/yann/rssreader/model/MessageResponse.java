package br.com.yann.rssreader.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(Include.NON_NULL)
public class MessageResponse {

  public static MessageResponseBuilder addMessage (String name, Object o){

    return MessageResponseBuilder.addMessage(name, o);

  }


  private class MessageResponseBuilder {

    @JsonValue
    public static Map<String,Object> messageElements = new HashMap<>();

    private static MessageResponseBuilder addMessage (String name, Object o){
      messageElements.put(name, o);
      return MessageResponseBuilder.addMessage(name, o).build();
    }

    private MessageResponseBuilder build (){
      return this;
    }

  }
}