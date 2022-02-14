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


  public List<User> getUsers() {
    return dao.findAll();
  }

  public void updateAnyUser (User user){
    User userByLogin = dao.findByLogin(user.getLogin());
    dao.save(userByLogin);
  }

}
