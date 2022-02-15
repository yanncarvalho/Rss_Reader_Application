package br.com.yann.rssreader.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.data.AdminDao;
import br.com.yann.rssreader.entity.User;

@Stateful
public class AdminService {

  @Inject
  @Named("Admin")
  AdminDao dao;


  public List<User> listUsers() {
    return dao.findAll();
  }

  public void updateAnyUser (User user){
    dao.update(user);
  }

  public User findAnyUserByUsername(String username) {
    return dao.findByUsername(username);
  }

  public void deleteAnyUser(String username) {
    dao.delete(dao.findByUsername(username));
  }


}
