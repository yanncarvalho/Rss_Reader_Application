package br.dev.yann.rssreader.service;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.dao.AuthAnyUserDao;
import br.dev.yann.rssreader.dto.UserDTO;
import br.dev.yann.rssreader.entity.User;

@Stateful
@Default
public class AuthAnyUserService {

  @Inject
  @Named("AuthAnyUser")
  private AuthAnyUserDao dao;

  public boolean hasUsername(String username){
    return (dao.findByUsername(username) != null);
  }

  public boolean hasUser(String username, long id){
    User user = dao.findByUsername(username);

    return (user != null && user.getId() == id);
  }

  public void save (UserDTO.Request.Save user){
      dao.save(new User(user));
  }

  public User isUserValid (UserDTO.Request.Save user){
    User userFound = dao.findByUsername(user.getUsername());

    if(userFound != null && userFound.authenticate(user.getPassword())){
      return userFound;
    }
    return null;
  }

  public User findByUsername (String username){
    return dao.findByUsername(username);
  }

  public void delete (String username){
    User findById = dao.findByUsername(username);
    dao.delete(findById);
  }

  //FIXME TEM QUE ATUALIZAR OS VALORES NA MAO
  public User update(String username, UserDTO.Request.Update user) {

    User findByUsername = dao.findByUsername(username);
    if (!findByUsername.getUsername().equals(user.getUsername()) && hasUsername(user.getUsername()))
          return  null;
          findByUsername.setId(findByUsername.getId());
          findByUsername.setPassword(user.getPassword());
    dao.update(findByUsername);
    return findByUsername;
  }
}
