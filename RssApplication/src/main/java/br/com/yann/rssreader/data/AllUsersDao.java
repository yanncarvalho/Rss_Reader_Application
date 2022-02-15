package br.com.yann.rssreader.data;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.yann.rssreader.entity.User;

@Stateless
@Named("allUser")
public class AllUsersDao {

  @PersistenceContext(name="rssreader")
  private EntityManager manager;


  public void save(User user) {
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
    //TODO melhorar
    manager.merge(user);

  }



}


