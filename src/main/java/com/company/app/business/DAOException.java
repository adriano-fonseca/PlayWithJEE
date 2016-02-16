package com.company.app.business;

import javax.ejb.ApplicationException;

@ApplicationException
public class DAOException extends RuntimeException {
	
	public DAOException(String mensagem) {
		super(mensagem);
	}

}
