package br.dev.yann.rssreader.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.dev.yann.rssreader.entity.User;

@Stateless
@Named("AuthAdmin")
public class AuthAdminDao  extends AuthAnyUserDao{

  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  @SuppressWarnings({"unchecked"})
  public List<User> findAll() {
    Query query = manager.createQuery("FROM "+User.class.getName());
   return (List<User>) query.getResultList();
 }

}

