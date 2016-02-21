package com.company.app.dao;

import javax.ejb.ApplicationException;

@ApplicationException
public class DAOException extends RuntimeException {
	
	public DAOException(String mensagem) {
		super(mensagem);
	}

}
