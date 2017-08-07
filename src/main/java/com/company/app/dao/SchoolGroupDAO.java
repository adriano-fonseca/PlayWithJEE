package com.company.app.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.company.app.dao.util.UtilHibernate;
import com.company.app.infra.RNImpl;
import com.company.app.model.SchoolGroup;


@Stateless
public class SchoolGroupDAO extends RNImpl<SchoolGroup> {
 
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(SchoolGroupDAO.class); 
  
  UtilHibernate utilHibernate;
  
  @PersistenceContext 
  private EntityManager entityManager;
  
  @PostConstruct
  public void init() {
    super.init(entityManager);
    utilHibernate = new UtilHibernate(entityManager, SchoolGroup.class);
  }
}
