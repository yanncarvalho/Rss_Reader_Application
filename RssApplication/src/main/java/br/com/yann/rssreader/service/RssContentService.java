package br.com.yann.rssreader.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.data.RssDao;
import br.com.yann.rssreader.entity.User;
import br.com.yann.rssreader.model.Rss;
import br.com.yann.rssreader.util.RssConvertor;

public class RssContentService {

  @Inject
  @Named("Rss")
  private RssDao dao;

  @Inject
  private JWTToken tokenJWT;

  @Inject
  private  RssConvertor converter;

  public Set<Rss> getContents(String token) {

    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    Set<String> urls = user.getUrlsRss();
    return urls.stream().map(url -> converter.getRss(url)).collect(Collectors.toSet());

  }

}
