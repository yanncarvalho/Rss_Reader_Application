package br.dev.yann.rssreader.service;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.auth.PasswordEncryption;
import br.dev.yann.rssreader.data.AuthAnyUserDao;
import br.dev.yann.rssreader.entity.User;


@Stateful
@Default
public class AuthAnyUserService {

  @Inject
  @Named("AuthAnyUser")
  private  AuthAnyUserDao dao;

  @Inject
  private PasswordEncryption crypto;

  public boolean hasUsername(String username){
    return (dao.findByUsername(username) != null);
  }


  public void save (User user){
    user.setPassword(crypto.hash(user.getPassword()));
    dao.save(user);
  }

  public boolean hasUser (User user){
    User userFound = dao.findByUsername(user.getUsername());
    //TODO CLASSE SEPRADA PARA VALIDAÇÃO?
    if (userFound != null && userFound.equals(user) && crypto.authenticate(user.getPassword(), userFound.getPassword()))
      return true;
   return false;
  }

  public User find (String username){

    return dao.findByUsername(username);
  }


  public void delete (String username){
    User findById = dao.findByUsername(username);
    dao.delete(findById);
  }
  //FIXME TEM QUE ATUALIZAR OS VALORES NA MAO
  public User update(String username, User user) {

    User findByUsername = dao.findByUsername(username);
    if (!findByUsername.getUsername().equals(user.getUsername()) && hasUsername(user.getUsername()))
          return  null;
    user.setId(findByUsername.getId());
    user.setPassword(crypto.hash(user.getPassword()));
    dao.update(user);
    return user;
  }
}
