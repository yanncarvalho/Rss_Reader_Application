package br.dev.yann.rssreader.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.dao.AuthAdminDao;
import br.dev.yann.rssreader.dto.UserDTO;
import br.dev.yann.rssreader.entity.User;

@Stateful
public class AuthAdminService {

  @Inject
  @Named("AuthAdmin")
  private AuthAdminDao dao;

  public List<User> findAllUsers() {
    return dao.findAllUsers();
  }

  public void updateUserAsAdmin (UserDTO.Request.Update user){
    dao.update(user);
  }

  public void deleteUserAsAdmin(Long id) {
    dao.delete(id);
  }
  public List<Long> findAllAdminsIds() {
    return dao.findAllAdminsIds();
  }

  public Long updateAndGetFirstId() {
     dao.updateFirstIdAsAdmin();
     return dao.findFirstId();
  }

  public boolean hasUsername(String username){
    return (dao.findByUsername(username) != null);
  }

  public User findUserByIdAsAdmin(Long id) {
    return dao.findById(id);
  }

  public User findUserByUsernameAsAdmin(String username) {
    return dao.findByUsername(username);
  }

  public boolean hasUsername(String username, Long id) {
    User findByUsername = dao.findByUsername(username);
    return (findByUsername != null && findByUsername.getId() != id);
  }


}
