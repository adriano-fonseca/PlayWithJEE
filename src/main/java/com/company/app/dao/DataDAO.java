package com.company.app.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.company.app.infra.RNImpl;
import com.company.app.model.Data;

@Stateless
public class DataDAO extends RNImpl<Data> {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		super.init(entityManager);
	}
	
}