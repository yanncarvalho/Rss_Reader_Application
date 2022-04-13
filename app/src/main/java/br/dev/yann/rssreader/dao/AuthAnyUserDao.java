package br.dev.yann.rssreader.dao;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.dev.yann.rssreader.entity.User;

@Stateless
@Named("AuthAnyUser")
@SuppressWarnings({"unchecked"})
public class AuthAnyUserDao {

  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  public void save(User user) {
     manager.persist(user);
  }

  public User findByUsername (String username) {
    Query query = manager.createQuery("SELECT u FROM users u WHERE u.username = :username");
    query.setParameter("username", username);
    return (User) query.getResultStream().findFirst().orElse(null);
  }

  public void delete(User user) {
    manager.remove(user);
  }

  //TODO AJEITAR
  public void update(User user) {
    manager.merge(user);
    manager.flush();
  }

}


