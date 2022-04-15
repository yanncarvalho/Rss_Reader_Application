package br.dev.yann.rssreader.dao;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.dev.yann.rssreader.dto.UserDTO;
import br.dev.yann.rssreader.entity.User;

@Stateless
@Named("AuthAnyUser")
@SuppressWarnings({"unchecked"})
public class AuthAnyUserDao {

  @PersistenceContext(name = "rssreader")
  private EntityManager manager;

  public void save(User user) {
    manager.persist(user);
  }

  public User findById(long id) {
    return manager.find(User.class, id);
  }

  public void delete(Long id) {
    manager.remove(this.findById(id));
  }

  public User update(UserDTO.Request.Update user) {

    User merge = manager.merge(this.findById(user.getId()));

    if (user.getName() != null) {
      merge.setName(user.getName());
    }

    if (user.getUsername() != null) {
      merge.setUsername(user.getUsername());
    }

    if (user.getPassword() != null) {
      merge.setPassword(user.getPassword());
    }

    manager.flush();

    return merge;
  }

  public User findByUsername (String username) {
    Query query = manager.createQuery("SELECT u FROM users u WHERE u.username = :username");
    query.setParameter("username", username);
    return (User) query.getResultStream().findFirst().orElse(null);
  }
}
