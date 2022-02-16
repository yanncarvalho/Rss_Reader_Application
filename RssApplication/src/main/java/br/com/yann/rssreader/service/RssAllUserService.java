package br.com.yann.rssreader.service;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.data.RssAllUsersDao;
import br.com.yann.rssreader.entity.User;

public class RssAllUserService {

  @Inject
  @Named("RssAllUsers")
  RssAllUsersDao dao;
  @Inject
  JWTToken tokenJWT;


  public Set<String> findAll(String token) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    return user.getUrlsRss();
  }


  public void deleteRss(String token, Set<String> rssUrl) {
      User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
      dao.deleteRss(rssUrl,user);
  }






}
