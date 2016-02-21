package com.company.app.exception;

import com.company.app.dao.DAOException;

/**
 * 
 * Exceção lançada quando um registro não é encontrado. 
 * 
 * @author mauro-flores
 *
 */
public class RecordNotFoundException extends DAOException {

  private static final long serialVersionUID = 1L;

  public RecordNotFoundException(String msg) {
    super(msg);
  }
}