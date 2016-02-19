package com.poc.teste;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.company.app.dao.GroupDAO;
import com.company.app.model.Group;
import com.company.app.model.School;

public class PopuleDataBaseForTests {

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
	    
	    Group group = new Group();
	    group.setNameGroup("TESTE");
		entityManager.getTransaction().begin();

		 GroupDAO groupDAO = new GroupDAO(entityManager);
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
//		}
	}