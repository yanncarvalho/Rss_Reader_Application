package br.dev.yann.rssreader.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.data.RssDao;
import br.dev.yann.rssreader.entity.User;
import br.dev.yann.rssreader.util.RssPagination;

public class RssContentService {

  @Inject
  @Named("Rss")
  private RssDao dao;

  public RssPagination getUserRssContents(String username, int page, int size, int offset) {
    User user  = dao.findByUsername(username);
    return new RssPagination (user.getUrlsRss(), page, size, offset);
  }

  public RssPagination convertRssUrls(List<String> urls, int page, int size, int offset) {
     return new RssPagination (urls, page, size, offset);
  }

}
