package com.company.app.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.company.app.model.Student;

@Stateless
public class StudentDAO {

    private DAO<Student> dao;
    
    @PersistenceContext(unitName = "AppDS")
    private EntityManager entityManager;
    
    public StudentDAO() {
        this.dao = new DAO<Student>(entityManager, Student.class);
      }
    
    public StudentDAO(EntityManager entityManager) {
      this.entityManager = entityManager;
      this.dao = new DAO<Student>(entityManager, Student.class);
    }

    public void adiciona(Student conta) {
      this.dao.adiciona(conta);
    }

    public List<Student> lista() {
      return this.dao.list();
    }

    public void remove(Student conta) {
      this.dao.remove(conta);
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
