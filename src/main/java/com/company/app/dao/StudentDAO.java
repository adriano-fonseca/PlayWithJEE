package com.company.app.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.company.app.infra.RNImpl;
import com.company.app.model.Student;

@Stateless
public class StudentDAO extends RNImpl<Student> {

    @PersistenceContext 
    private EntityManager entityManager;
    
    @PostConstruct
    public void init() {
      super.init(entityManager);
    }
    
    public Student searchByNameWithPositionParameter(String name) {
      Query query = entityManager.createQuery("select c from Student c where c.nameStudent = ?1");
      query.setParameter(1, name);
      return (Student) query.getSingleResult();
    }
    
    public Student searchByNameWithNamedParameter(String name) {
      Query query = entityManager.createQuery("select c from Student c where c.nameStudent = :name");
      query.setParameter("name", name);
      
      return (Student) query.getSingleResult();
    }
    
    public Student searchByNameWithTypedQuery(String name) {
      TypedQuery<Student> query = entityManager.createQuery("select c from Student c where c.nameStudent = :name", Student.class);
      query.setParameter("name", name);
      return query.getSingleResult();
    }
    
    
}
