package br.dev.yann.rssreader.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.dev.yann.rssreader.auth.PasswordEncrypt;
import br.dev.yann.rssreader.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Entity(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;

  @Getter
  private String username;

  private String password;

  @Getter
  private String name;

  @Column(name = "is_admin")
  @JsonProperty
  @Getter
  private boolean isAdmin = false;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name="rss_urls", joinColumns = @JoinColumn(name="id") )
  private List<String> urls = new ArrayList<>();

  public User() {
  }

  //TODO tirar isso
  public User(UserDTO.Request.Save save) {
    name = save.getName();
    username = save.getUsername();
    this.setPassword(save.getPassword());
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

   public boolean authenticate(String password) {
    return PasswordEncrypt.authenticate(this.password, password);
  }

}
