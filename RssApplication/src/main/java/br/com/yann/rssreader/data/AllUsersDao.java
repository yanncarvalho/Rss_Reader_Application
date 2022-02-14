package br.com.yann.rssreader.data;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.yann.rssreader.entity.User;

@Stateless
@Default
@Named("allUser")
public class AllUsersDao {

  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  public void save(User user) {
    manager.persist(user);
  }

  public User findByLogin(String login) {
    return manager.find(User.class,login);
  }
}


