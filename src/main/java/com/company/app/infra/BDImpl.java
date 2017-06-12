package com.company.app.infra;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.company.app.model.BaseBean;

public class BDImpl<T extends BaseBean<? extends Serializable>> {

	public BDImpl() { super(); }

	private static final String MESSAGE_REG_JA_EXCLUIDO_TENTE_NOVAMENTE = "Recarregue a p�gina e verifique se o registro j� n�o havia sido exclu�do anteriormente. Se for o caso, tente novamente."; 
	
	public BDImpl(EntityManager em) {
		
	}
	
}
