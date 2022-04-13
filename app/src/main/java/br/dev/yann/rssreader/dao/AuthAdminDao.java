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
@SuppressWarnings({"unchecked"})
public class AuthAdminDao  extends AuthAnyUserDao{

  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  public List<User> findAllUsers() {
    Query query = manager.createQuery("FROM "+User.class.getName());
    return (List<User>) query.getResultList();
  }


  public List<String> findAllAdminUsernames() {
    Query query = manager.createQuery("SELECT u.username "
                                        + "FROM users u "
                                        + "WHERE u.isAdmin = 1 ", String.class);

    return (List<String>) query.getResultList();
  }


  public String updateFirstIdAsAdmin() {
    //TODO yet to be completed
    Query query = manager.createQuery("SELECT u.username "
    + "FROM users u "
    + "WHERE u.isAdmin = 1 ", String.class);
    return null;
  }

}
