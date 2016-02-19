package com.company.app.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.company.app.dao.SchoolGroupDAO;
import com.company.app.model.School;
import com.company.app.model.SchoolGroup;

public class PopuleDataBaseForTests {
/*
	private EntityManager entityManager;
	
	private EntityManagerFactory factory;

	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory("appDS");
		entityManager = factory.createEntityManager();
	}
	
	@Test
	public void deveriaInserirUmaListaDeContasEMovimentacoesSomenteParaTestes() throws Exception {
//		limpaBaseDeDados();
		
	    School schoolA = new School();
	    schoolA.setNameSchool("Tiririca");
	    
	    SchoolGroup group = new SchoolGroup();
	    group.setNameSchoolGroup("TESTE");
		entityManager.getTransaction().begin();

		 SchoolGroupDAO groupDAO = new SchoolGroupDAO(entityManager);
		 entityManager.getTransaction().commit();
		
//		List<Conta> contas = contaDAO.lista();
//		List<Movimentacao> movimentacoes = movimentacaoDAO.lista();
//		
//		assertEquals(3, contas.size());
//		assertEquals(8, movimentacoes.size());
	}
	
//	private void limpaBaseDeDados() {
//		entityManager.getTransaction().begin();
//		removeMovimentacoes();
//		removeContas();
//		entityManager.getTransaction().commit();
//	}
//
//	private void removeContas() {
//		ContaDAO contaDAO = new ContaDAO(entityManager);
//		List<Conta> contas = contaDAO.lista();
//		for (Conta conta : contas) {
//			contaDAO.remove(conta);
//		}
//	}
//
//	private void removeMovimentacoes() {
//		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(entityManager);
//		List<Movimentacao> lista = movimentacaoDAO.lista();
//		for (Movimentacao movimentacao : lista) {
//			movimentacaoDAO.remove(movimentacao);
//		}*/
	}