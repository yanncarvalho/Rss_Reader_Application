package br.com.yann.rssreader.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import br.com.yann.rssreader.data.UserDao;
import br.com.yann.rssreader.entity.User;

@Stateful
public class UserService {

  @Inject
  UserDao dao;

  public void saveNewUser (User user){
    dao.save(user);
  }

  public List<User> getUsers() {
    return dao.findAll();
  }
}
