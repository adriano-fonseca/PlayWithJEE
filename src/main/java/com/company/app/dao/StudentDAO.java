package com.company.app.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.company.app.model.Book;

@Stateless
public class StudentDAO extends DAO<Book> {

    @PersistenceContext 
    private EntityManager entityManager;
    
    @PostConstruct
    public void init() {
      super.init(entityManager,Book.class);
    }
    
    public Book searchByNameWithPositionParameter(String name) {
      Query query = entityManager.createQuery("select c from Student c where c.nameStudent = ?1");
      query.setParameter(1, name);
      return (Book) query.getSingleResult();
    }
    
    public Book searchByNameWithNamedParameter(String name) {
      Query query = entityManager.createQuery("select c from Student c where c.nameStudent = :name");
      query.setParameter("name", name);
      
      return (Book) query.getSingleResult();
    }
    
    public Book searchByNameWithTypedQuery(String name) {
      TypedQuery<Book> query = entityManager.createQuery("select c from Student c where c.nameStudent = :name", Book.class);
      query.setParameter("name", name);
      return query.getSingleResult();
    }
}
