package br.com.yann.rssreader.data;

import java.util.Set;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.yann.rssreader.entity.User;


@Named("RssAllUsers")
public class RssAllUsersDao extends AuthAllUsersDao {

  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  public void deleteRss(Set<String> rssUrls, User user){
    manager.createQuery("DELETE FROM rss_urls").executeUpdate();
   // SET FROM rss_urls WHERE urls = 'https://www.hobo-web.co.uk/feed/' AND id=1").executeUpdate();



  }



}
