package com.company.app.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.company.app.model.SchoolGroup;


@Stateless
public class SchoolGroupDAO {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(TeacherDAO.class); 
  
  private DAO<SchoolGroup> dao;
  
  @PersistenceContext(unitName = "AppDS")
  private EntityManager entityManager;
  
  public SchoolGroupDAO(){
       this.dao = new DAO<SchoolGroup>(entityManager, SchoolGroup.class);
  }
  
  public List<SchoolGroup> list() {
      return this.dao.list();
  }
  
  public void add(SchoolGroup group) {
      this.dao.adiciona(group);
  }
  
  
}
