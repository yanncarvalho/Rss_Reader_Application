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


  public List<String> findAll(Long id) {
    User user  = dao.findById(id);
    return user.getUrlsRss();
  }

  public void deleteRss(Long id, List<String> rssUrls) {
    User user  = dao.findById(id);
    user.removeAllUrlsRss(rssUrls);
    dao.deleteRss(user);
  }

  public void addRss(Long id, List<String> rssUrls) {
    User user  = dao.findById(id);
    user.addAllUrlRss(rssUrls);
    dao.addRss(user);
  }

  public void deleteAllRss(Long id) {
    User user  = dao.findById(id);
    user.cleanUrlsRss();
    dao.deleteRss(user);
  }

  public List<String> getRssList(Long id, List<String> rssUrls) {
     User user  = dao.findById(id);
     rssUrls.retainAll(user.getUrlsRss());
    return rssUrls;
  }


}
