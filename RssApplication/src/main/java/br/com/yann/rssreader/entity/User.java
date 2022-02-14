package br.com.yann.rssreader.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

  private String name;
  @Id
  private String login;
  private String password;
  private boolean admin = false;


  public User() {
  }

  // public List<String> getUrl() {
  //   return url;
  // }

  // public void setUrl(List<String> url) {
  //   this.url = url;
  // }

  public boolean getAdmin(){
    return this.admin;
  }

  public void setAdmin(boolean admin){
    this.admin = admin;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLogin() {
    return login;
  }


  public void setLogin(String login) {
    this.login = login;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((login == null) ? 0 : login.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
      if (login == null) {
      if (other.login != null)
        return false;
    } else if (!login.equals(other.login))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    return true;
  }

}
