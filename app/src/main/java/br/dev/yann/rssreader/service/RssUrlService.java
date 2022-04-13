package br.dev.yann.rssreader.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.dao.RssDao;
import br.dev.yann.rssreader.entity.User;

public class RssUrlService {

  @Inject
  @Named("Rss")
  private RssDao dao;


  public List<String> findAll(String username) {
    User user  = dao.findByUsername(username);
    return user.getUrlsRss();
  }

  public void deleteRss(String username, List<String> rssUrls) {
    User user  = dao.findByUsername(username);
    user.removeAllUrlsRss(rssUrls);
    dao.deleteRss(user);
  }

  public void addRss(String username, List<String> rssUrls) {
    User user  = dao.findByUsername(username);
    user.addAllUrlRss(rssUrls);
    dao.addRss(user);
  }

  public void deleteAllRss(String username) {
    User user  = dao.findByUsername(username);
    user.cleanUrlsRss();
    dao.deleteRss(user);
  }

  public List<String> getRssList(String username, List<String> rssUrls) {
     User user  = dao.findByUsername(username);
      rssUrls.retainAll(user.getUrlsRss());
    return rssUrls;
  }


}
