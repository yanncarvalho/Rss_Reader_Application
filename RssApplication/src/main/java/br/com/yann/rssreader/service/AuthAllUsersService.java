package br.com.yann.rssreader.service;

import java.util.Map;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.auth.EncryptPassword;
import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.data.AuthAllUsersDao;
import br.com.yann.rssreader.entity.User;


@Stateful
@Default
public class AuthAllUsersService {

  @Inject
  @Named("AuthAllUsers")
  private  AuthAllUsersDao dao;

  @Inject
  private JWTToken tokenJWT;

  @Inject
  private EncryptPassword crypto;

  private boolean hasUsername(String username){
    return (dao.findByUsername(username) != null);
  }

  public String save (User user){
    if (hasUsername(user.getUsername()))
        return null;
    user.setPassword(crypto.hash(user.getPassword()));
    dao.save(user);
    return tokenJWT.encode(user);
  }

  public String login (User user){
    User userFound = dao.findByUsername(user.getUsername());
    //TODO CLASSE SEPRADA PARA VALIDAÇÃO?
    if (userFound != null && userFound.equals(user) && crypto.authenticate(user.getPassword(), userFound.getPassword()))
      return tokenJWT.encode(userFound);
   return null;
  }

  public User find (String token){
    Map<String, Object> decode = tokenJWT.decode(token);
    if (decode == null)
      return null;
    return dao.findByUsername((String)decode.get("username"));

  }


  public void delete (String token){
    Map<String, Object> decode = tokenJWT.decode(token);
    User findById = dao.findByUsername((String)decode.get("username"));
    dao.delete(findById);
  }

  public String update(String token, User user) {
    Map<String, Object> decode = tokenJWT.decode(token);
    User findByUsername = dao.findByUsername((String)decode.get("username"));
    if (!findByUsername.getUsername().equals(user.getUsername()) && hasUsername(user.getUsername()))
          return  null;
    user.setId(findByUsername.getId());
    user.setPassword(crypto.hash(user.getPassword()));
    dao.update(user);
    return tokenJWT.encode(user);
  }
}
