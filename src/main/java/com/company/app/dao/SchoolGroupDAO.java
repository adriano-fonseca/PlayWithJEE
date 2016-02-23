package com.company.app.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.company.app.model.SchoolGroup;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SchoolGroupDAO extends DAO<SchoolGroup> {
 
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(SchoolGroupDAO.class); 
  
  @PersistenceContext 
  private EntityManager entityManager;
  
  @PostConstruct
  public void init() {
    super.init(entityManager,SchoolGroup.class);
  }
  
}
