package br.dev.yann.rssreader.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.auth.PasswordEncryption;
import br.dev.yann.rssreader.dao.AuthAdminDao;
import br.dev.yann.rssreader.entity.User;

@Stateful
public class AuthAdminService {

  @Inject
  @Named("AuthAdmin")
  private AuthAdminDao dao;

  @Inject
  private PasswordEncryption crypto;

  public List<User> findAllUsers() {
    return dao.findAllUsers();
  }

  //TODO ajeitar O ATUALIZAR TEM QUE TER OS VALORES DO USUARIO SETADOS
  public void updateAnyUser (User user){
    user.setPassword(crypto.hash(user.getPassword()));
    dao.update(user);
  }

  public User findAnyUserByUsername(String username) {
    return dao.findByUsername(username);
  }

  public void deleteAnyUser(String username) {
    dao.delete(dao.findByUsername(username));
  }
  public List<String> findAllAdminUsernames() {
    List<String> adminsUsernames = dao.findAllAdminUsernames();

    return adminsUsernames;
  }

  public String updateFirstIdAsAdmin() {
     return dao.updateFirstIdAsAdmin();
  }

}
