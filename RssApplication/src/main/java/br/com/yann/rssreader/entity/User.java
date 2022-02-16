package br.com.yann.rssreader.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String name;
  @Column(name = "is_admin")
  private boolean admin = false;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name="rss_urls", joinColumns = @JoinColumn(name="id") )
  @OrderColumn
  private Set<String> urls = new HashSet<>();

  public User() {
  }

  public Set<String> getUrlsRss() {
    return this.urls;
  }
  public boolean removeUrlRss(String url) {
    return this.urls.remove(url);
  }
  public boolean addUrlRss(String url) {
    return this.urls.add(url);
  }
  public boolean addUrlRssAll (Set<String> urls){
    return this.urls.addAll(urls);
  }
  public boolean removeUrlsRssAll (Set<String> urls){
    return this.urls.removeAll(urls);
  }
  public boolean isAdmin(){
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

  @Override
  public String toString() {
    return "User [admin=" + admin + ", id=" + id + ", name=" + name + ", password=" + password + ", rss=" + urls
        + ", username=" + username + "]";
  }



}
