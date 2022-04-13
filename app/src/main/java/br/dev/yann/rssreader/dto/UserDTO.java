package br.dev.yann.rssreader.dto;

import java.util.List;

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

  private interface Id {
    public Long getId();
  }

  private interface Password {
    public String getPassword();
  }

  private interface AdminRole {
    public Boolean isAdmin();
  }

  public enum Request {;
    @Data
    private static class Base {};

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Save extends Base implements Password, Username, Name {
      private String password;
      private String username;
      private String name;
    };


    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Update extends Base implements Password, Username, Name, Id, AdminRole {
      private String password;
      private String username;
      private String name;
      private Long id;
      private Boolean isAdmin;

      @Override
      public Boolean isAdmin() {
         return isAdmin;
      }
    }

  };

}
