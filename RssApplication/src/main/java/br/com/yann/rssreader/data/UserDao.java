package br.com.yann.rssreader.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.yann.rssreader.entity.User;

@Stateless
public class UserDao {
  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  public void save(User user) {
    manager.persist(user);
  }
  //TODO USAR O INSTANCEOF
  public List<User> findAll() {
    Query query = manager.createNativeQuery("SELECT * FROM USER");
    return (List<User>) query.getResultList();
  }

}
