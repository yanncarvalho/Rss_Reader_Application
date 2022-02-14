package br.com.yann.rssreader.service;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.data.AllUsersDao;
import br.com.yann.rssreader.entity.User;

@Stateful
@Default
public class AllUsersService {

  @Inject
  @Named("allUser")
  AllUsersDao dao;
  @Inject
  JWTToken token;

  public String saveNewUser (User user){
    dao.save(user);
    return token.encode(user.hashCode());

  }

  public String getUser (User user){
    User userByLogin = dao.findByLogin(user.getLogin());


    if (userByLogin != null && userByLogin.equals(user))
    {
        //TODO GUARDAR TOKEN
        return token.encode(user.hashCode());
      }
   return null;

  }


}
