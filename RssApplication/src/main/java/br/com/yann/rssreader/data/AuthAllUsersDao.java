package br.com.yann.rssreader.data;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.yann.rssreader.entity.User;

@Stateless
@Named("AuthAllUsers")
public class AuthAllUsersDao {

  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  public void save(User user) {
    user.addUrlRss("https://developers.google.com/web/updates/rss.xml");
    user.addUrlRss("https://www.hobo-web.co.uk/feed/");
    user.addUrlRss("http://feeds.feedburner.com/RachelNabors");
     manager.persist(user);
  }


  //TODO MELHORAR ESSA QUERY
  public User findByUsername (String username) {
    Query query = manager.createQuery("SELECT u FROM users u WHERE u.username = :username").setParameter("username", username);
    if(!query.getResultStream().findAny().isPresent())
      return null;
    return (User) query.getSingleResult();
  }


  public void delete(User user) {
    manager.remove(user);
  }


  public void update(User user) {
    manager.merge(user);
    manager.flush();
  }

}

