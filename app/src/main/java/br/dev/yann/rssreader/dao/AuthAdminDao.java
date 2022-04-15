package br.dev.yann.rssreader.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.dev.yann.rssreader.entity.User;

@Stateless
@Named("AuthAdmin")
@SuppressWarnings({ "unchecked" })
public class AuthAdminDao extends AuthAnyUserDao {

  @PersistenceContext(name = "rssreader")
  private EntityManager manager;

  public List<User> findAllUsers() {
    Query query = manager.createQuery("FROM " + User.class.getName());
    return (List<User>) query.getResultList();
  }

  public List<Long> findAllAdminsIds() {
    Query query = manager.createQuery("SELECT u.id "
        + "FROM users u "
        + "WHERE u.admin = 1 ", Long.class);
    return (List<Long>) query.getResultList();
  }

  public void updateFirstIdAsAdmin() {
    manager.createNativeQuery("UPDATE rssreader.users, "
        + "(SELECT MIN(rssreader.users.id) AS id FROM rssreader.users) AS user_min "
        + "SET is_admin = 1 "
        + "WHERE users.id = user_min.id")
        .executeUpdate();
  }

  public Long findFirstId() {
    Query query = manager.createQuery("SELECT u.id "
        + "FROM users u "
        + "WHERE u.id = (SELECT MIN(u.id) FROM users u)");
    return (Long) query.getSingleResult();
  }

}
