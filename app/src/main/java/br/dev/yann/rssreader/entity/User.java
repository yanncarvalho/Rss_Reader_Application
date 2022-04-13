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

@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String name;
  @Column(name = "is_admin")
  private boolean isAdmin = false;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name="rss_urls", joinColumns = @JoinColumn(name="id") )
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
    return this.isAdmin;
  }

  public void setAdmin(boolean admin){
    this.isAdmin = admin;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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

    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

}
