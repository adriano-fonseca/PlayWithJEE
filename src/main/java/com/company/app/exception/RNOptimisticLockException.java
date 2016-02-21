package com.company.app.exception;

import com.company.app.dao.DAOException;

public class RNOptimisticLockException extends DAOException {

  private static final long serialVersionUID = 1L;
  
  private static final String MSG = "Record was chancged for another user.";

  public RNOptimisticLockException() {
    super(MSG);
  }
  
  public RNOptimisticLockException(String msg) {
    super(msg);
  }
}