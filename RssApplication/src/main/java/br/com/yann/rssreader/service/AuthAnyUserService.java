package br.com.yann.rssreader.service;

import java.util.Map;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.auth.PasswordEncryption;
import br.com.yann.rssreader.auth.JWTToken;
import br.com.yann.rssreader.data.AuthAnyUserDao;
import br.com.yann.rssreader.entity.User;


@Stateful
@Default
public class AuthAnyUserService {

  @Inject
  @Named("AuthAnyUser")
  private  AuthAnyUserDao dao;

  @Inject
  private JWTToken tokenJWT;

  @Inject
  private PasswordEncryption crypto;

  private boolean hasUsername(String username){
    return (dao.findByUsername(username) != null);
  }

  public String save (User user){
    if (hasUsername(user.getUsername()))
        return null;
    user.setPassword(crypto.hash(user.getPassword()));
    dao.save(user);
    return tokenJWT.hash(user);
  }

  public String login (User user){
    User userFound = dao.findByUsername(user.getUsername());
    //TODO CLASSE SEPRADA PARA VALIDAÇÃO?
    if (userFound != null && userFound.equals(user) && crypto.authenticate(user.getPassword(), userFound.getPassword()))
      return tokenJWT.hash(userFound);
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
  //FIXME TEM QUE ATUALIZAR OS VALORES NA MAO
  public String update(String token, User user) {
    Map<String, Object> decode = tokenJWT.decode(token);
    User findByUsername = dao.findByUsername((String)decode.get("username"));
    if (!findByUsername.getUsername().equals(user.getUsername()) && hasUsername(user.getUsername()))
          return  null;
    user.setId(findByUsername.getId());
    user.setPassword(crypto.hash(user.getPassword()));
    dao.update(user);
    return tokenJWT.hash(user);
  }
}
