package com.company.app.infra;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.company.app.dao.util.UtilDAO;
import com.company.app.exception.DAOException;
import com.company.app.exception.RNOptimisticLockException;
import com.company.app.exception.RecordNotFoundException;
import com.company.app.model.BaseBean;


public class OldDAO<Bean extends BaseBean<? extends Serializable>> {

  private EntityManager em;

  private Class<Bean>   className;
  protected AppQuery<Bean> BD;

  protected void init(EntityManager em, Class<Bean> className) {
    this.em = em;
    this.className = className;
  }

  public Bean add(Bean t) {
    if (em.contains(t)) {
      throw new RuntimeException("Trying add an object already menaged.");
    }
    em.persist(t);
    em.flush();
    return t;
  }

  public Bean change(Bean t) {
    HashMap<String, String> msg = null;
    try {
      Bean queryBean = find(t);
      if (queryBean == null) {
        msg = new HashMap<String, String>();
        msg.put("message", "Record not found!");
        throw new RecordNotFoundException(msg);
      }
    } catch (DAOException e) {
      msg.put("message", "Record not found!");
      throw new RecordNotFoundException(msg);
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
  public HashMap<String, String> remove(Bean t) {
    Object conteudoPk = t.getId();
    HashMap<String, String> msg = null;
    
    try {
      Bean entityFind = (Bean) em.find(t.getClass(), conteudoPk);
      if(entityFind==null){
    	  msg = new HashMap<String, String>();
    	  msg.put("message","Record not found.");
          if (msg != null) {
            throw new RecordNotFoundException(msg);
          }
      }
      em.remove(entityFind);
      try {
        em.flush();
        msg = new HashMap<String, String>();
        msg.put("message", "Record removed!");
      } catch (NullPointerException e) {
        // see forum
        // http://www.eclipse.org/forums/index.php/m/799970/#msg_799970
        throw new RNOptimisticLockException();
      }
    } catch (EntityNotFoundException e) {
        msg.put("message","Record not found.");
      if (msg != null) {
        throw new RecordNotFoundException(msg);
      }
    }
    return msg;
  }
  
  public Bean find(Bean ed) {
  	//return find(new IseQueryVO.Builder(ed).build());
    Bean entityReturned = this.em.find(className, ed.getId());
    HashMap<String, String> msg = null;
    if (entityReturned == null) {
      msg = new HashMap<String, String>();
      msg.put("message", "Record not found!");
      if (msg != null) {
        throw new RecordNotFoundException(msg);
      }
    }
    return entityReturned;
  }
}