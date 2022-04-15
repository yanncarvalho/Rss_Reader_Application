package br.dev.yann.rssreader.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.dao.RssDao;
import br.dev.yann.rssreader.entity.User;
import br.dev.yann.rssreader.util.RssPagination;

public class RssContentService {

  @Inject
  @Named("Rss")
  private RssDao dao;

  public RssPagination getUserRssContents(Long id, int page, int size, int offset) {
    User user  = dao.findById(id);
    return new RssPagination (user.getUrlsRss(), page, size, offset);
  }

  public RssPagination convertRssUrls(List<String> urls, int page, int size, int offset) {
     return new RssPagination (urls, page, size, offset);
  }

}
