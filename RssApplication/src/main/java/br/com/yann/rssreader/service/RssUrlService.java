package br.com.yann.rssreader.service;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.data.RssDao;
import br.com.yann.rssreader.entity.User;

public class RssUrlService {

  @Inject
  @Named("Rss")
  private RssDao dao;

  @Inject
  private JWTToken tokenJWT;

  public List<String> findAll(String token) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    return user.getUrlsRss();
  }

  public void deleteRss(String token, Set<String> rssUrls) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    user.removeUrlsRssAll(rssUrls);
    dao.deleteRss(user);
  }

  public void addRss(String token, Set<String> rssUrls) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    user.addUrlRssAll(rssUrls);
    dao.addRss(user);
  }

  public void deleteAllRss(String token) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    user.cleanUrlsRss();
    dao.deleteRss(user);
  }

  public Set<String> hasRss(String token, Set<String> rssUrls) {
     User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
      rssUrls.retainAll(user.getUrlsRss());
    return rssUrls;
  }


}
