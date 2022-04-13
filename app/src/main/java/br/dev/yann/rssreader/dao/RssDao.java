package br.dev.yann.rssreader.dao;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.dev.yann.rssreader.entity.User;


@Named("Rss")
@RequestScoped
public class RssDao extends AuthAnyUserDao {

  @PersistenceContext(name="rssreader")
  private EntityManager manager;

  @Resource
  private UserTransaction userTransaction;

  public void deleteRss(User user){

    try {
      userTransaction.begin();
        manager.merge(user);
      userTransaction.commit();
     } catch (NotSupportedException| SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            //TODO TRATAR
        e.printStackTrace();
      }
  }

  public void addRss(User user){

    try {
      userTransaction.begin();
        manager.merge(user);
      userTransaction.commit();
     } catch (NotSupportedException| SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            //TODO TRATAR
        e.printStackTrace();
      }
  }



}
