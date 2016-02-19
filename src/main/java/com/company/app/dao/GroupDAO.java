package com.company.app.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.company.app.model.Group;
import com.company.app.model.Student;


@Stateless
public class GroupDAO {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(TeacherDAO.class); 
  
  private DAO<Group> dao;
  
  @PersistenceContext(unitName = "AppDS")
  private EntityManager entityManager;
  
  public GroupDAO(){
       this.dao = new DAO<Group>(entityManager, Group.class);
  }
  
  public GroupDAO(EntityManager entityManager){
	    this.entityManager = entityManager;
	    this.dao = new DAO<Group>(entityManager, Group.class);
  }
  
  public List<Group> list() {
      return this.dao.list();
  }
  
  public void add(Group group) {
      this.dao.adiciona(group);
  }
  
  
}
