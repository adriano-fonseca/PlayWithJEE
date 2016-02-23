package com.company.app.exception;

import java.util.HashMap;


/**
 * 
 * Exceção lançada quando um registro não é encontrado. 
 * 
 * @author mauro-flores
 *
 */
public class RecordNotFoundException extends DAOException {

  private static final long serialVersionUID = 1L;

  public RecordNotFoundException(HashMap<String, String> msg) {
    super(msg.get("message"));
  }
}