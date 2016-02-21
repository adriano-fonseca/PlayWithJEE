package com.company.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.company.app.dao.util.UtilDAO;
import com.company.app.exception.RNOptimisticLockException;
import com.company.app.exception.RecordNotFoundException;
import com.company.app.model.BaseBean;

public class DAO<Bean extends BaseBean<? extends Serializable>> {

  private EntityManager em;
  
  private Class<Bean> className;

  protected void init(EntityManager em, Class<Bean> className) {
    this.em = em;
    this.className = className;
  }

  public Bean add(Bean t) {
    if (em.contains(t)) {
      throw new RuntimeException(
          "Tentando incluir um objeto que já está gerenciado.");
    }
    em.persist(t);
    em.flush();
    return t;
  }
  
  public Bean Change(Bean t){
      try {
        Bean queryBean = find(t);
        if (queryBean == null) {
          throw new RecordNotFoundException("Record not foud!");
        }
      } catch (DAOException e) {
        // troca a mensagem de consulta pela de alteracao
        throw new RecordNotFoundException(e.getMessage());
      }

    Bean managed = em.merge(t);
    if (UtilDAO.isOpenJpa(em)) {
      UtilDAO.forcaMergeNull(t, managed);
    }
    em.flush();
    return managed;
  }

  @SuppressWarnings("unchecked")
  public List<Bean> list() {
    return em.createQuery("select t from " + className.getName() + " t").getResultList();
  }

  @SuppressWarnings("unchecked")
  public void remove(Bean t) {
      Object conteudoPk = t.getId();
      try {
        Bean entityFind = (Bean) em.find(t.getClass(), conteudoPk);
        em.remove(entityFind);
        try {
          em.flush();
        } catch (NullPointerException e) {
          // see forum
          // http://www.eclipse.org/forums/index.php/m/799970/#msg_799970
          throw new RNOptimisticLockException();
        }
      } catch (EntityNotFoundException e) {
        String msg = "Record not found.";
        if (msg != null) {
          throw new RecordNotFoundException(msg);
        }
      }
    }
  
  public Bean find(Bean t) {
    Bean entityReturned = this.em.find(className, t.getId());
    if (entityReturned == null) {
      String msg = "Record not found.";
      if (msg != null) {
        throw new RecordNotFoundException(msg);
      }
    }
    return entityReturned;
  }
}