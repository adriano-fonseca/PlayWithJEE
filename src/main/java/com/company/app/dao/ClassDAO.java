package com.company.app.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;


@Stateless
public class ClassDAO {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(TeacherDAO.class); 
  
  private DAO<ClassDAO> dao;
  
  @PersistenceContext(unitName = "AppDS")
  private EntityManager entityManager;
  
  public ClassDAO(EntityManager entityManager){
    this.em = entityManager;
    this.dao = new DAO<ClassDAO>(entityManager, ClassDAO.class);
  }
  
  @PersistenceContext(unitName = "AppDS")
  private EntityManager em;
  
}
