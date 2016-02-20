package com.company.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.company.app.business.DAOException;

public class DAO<T> {

  private EntityManager entityManager;
  
  private Class<T> className;

  protected void init(EntityManager em, Class<T> className) {
    this.entityManager = em;
    this.className = className;
  }

  public void add(T t) {
    if(this.entityManager==null){
      throw new DAOException("Erro entityManager");
    }
    this.entityManager.persist(t);
  }

  @SuppressWarnings("unchecked")
  public List<T> list() {
    return entityManager.createQuery("select t from " + className.getName() + " t").getResultList();
  }

  public void remove(T t) {
    this.entityManager.remove(t);
  }
  
}