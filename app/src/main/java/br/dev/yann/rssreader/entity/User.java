package br.dev.yann.rssreader.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.dev.yann.rssreader.auth.PasswordEncrypt;
import lombok.ToString;
@ToString
@Entity(name = "users")
public class User {

  @Id
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  private String password;

  private String name;

  @Column(name = "is_admin")

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
   private boolean admin = false;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> urls = new ArrayList<>();

  public User() {
  }

  public List<String> getUrlsRss() {
    return Collections.unmodifiableList(this.urls);
  }

  public boolean removeUrlRss(String url) {
    return this.urls.remove(url);
  }

  public boolean containsUrlRss(String url) {
    return this.urls.contains(url);
  }

  public boolean containsAllUrlRss(List<String> urls) {
    return this.urls.containsAll(urls);
  }

  public boolean addUrlRss(String url) {
    return this.urls.add(url);
  }

  public boolean addAllUrlRss (List<String> urls){
    return this.urls.addAll(urls);
  }

  public boolean removeAllUrlsRss (List<String> urls){
    return this.urls.removeAll(urls);
  }

  public void cleanUrlsRss() {
     this.urls.clear();
  }

  public boolean isAdmin(){
    return this.admin;
  }

  public void setAdmin(boolean admin){
    this.admin = admin;
  }


  public void setPassword(String password) {
    this.password = PasswordEncrypt.hash(password);
  }

  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }


   public boolean authenticate(String password) {
    return PasswordEncrypt.authenticate(password, this.password);
   }

}
