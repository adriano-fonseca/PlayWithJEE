package com.company.app.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.sql.JoinType;

import com.company.app.dao.util.PropriedadesLista;
import com.company.app.dao.util.UtilHibernate;
import com.company.app.model.StudentSchoolGroup;


@Stateless
public class StudentSchoolGroupDAO extends DAO<StudentSchoolGroup> {
 
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(StudentSchoolGroup.class); 
  
  UtilHibernate utilHibernate;
  
  
  @PersistenceContext 
  private EntityManager entityManager;
  
  @PostConstruct
  public void init() {
  	super.init(entityManager,StudentSchoolGroup.class);
    utilHibernate = new UtilHibernate(entityManager, StudentSchoolGroup.class);
  }
  
  @TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<StudentSchoolGroup> listWithStudentLoaded(StudentSchoolGroup studentSchoolGroup, PropriedadesLista qp) {
  	DetachedCriteria dc = DetachedCriteria.forClass(StudentSchoolGroup.class,"studentSchoolGroup");
    dc.createAlias("studentSchoolGroup.student", "student",JoinType.LEFT_OUTER_JOIN);
    dc.createAlias("studentSchoolGroup.schoolGroup", "schoolGroup",JoinType.LEFT_OUTER_JOIN);
    return (List<StudentSchoolGroup>) utilHibernate.lista(dc, qp);
	}
  
}
