package com.company.app.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.company.app.infra.RNImpl;
import com.company.app.model.Diff;

@Stateless
public class DiffDAO extends RNImpl<Diff> {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		super.init(entityManager);
	}
	
	public Diff findByIdFetchData(Long id){
		Query query = entityManager.createQuery("select d from Diff d join fetch d.listData where d.idDiff =:id");
		query.setParameter("id", id);
		return (Diff) query.getSingleResult();
	}
	
	@Override
	public List<Diff> list(Diff ed) {
		List<Diff> list = this.entityManager.createQuery("SELECT NEW com.company.app.model.Diff(d.idDiff) FROM Diff d").getResultList();
		return list;
	}	
}