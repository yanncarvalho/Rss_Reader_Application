package br.dev.yann.rssreader.dto;

import java.util.List;
import br.dev.yann.rssreader.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

public enum UserDTO {
  ;

  private interface Name {
    public String getName();
  }

   private interface Username {
    public String getUsername();
  }

  private interface Password {
    public String getPassword();
  }

  private interface Id {
    public Long getId();
  }


  private interface Urls{
    public List<String> getUrls();
  }

  public enum Request {;
    @Data
    private static class Base {};

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Login extends Base implements Password, Username {
      private String password;
      private String username;
    };


    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Update extends Base implements Password, Username, Name, Id{
      private String password;
      private String username;
      private String name;
      @JsonProperty(access = JsonProperty.Access.READ_ONLY)
      private Long id;
    };

   };

   public enum Response {;

    @Data
    private static class Base {};

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Find extends Base implements Username, Name, Urls{
      private String username;
      private String name;
      private List<String> urls;

     public Find(User user){
        this.username = user.getUsername();
        this.name = user.getName();
        this.urls = user.getUrlsRss();
      }

    }

  }

}
