package com.company.app.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseBean<PK extends Serializable> implements Serializable, Cloneable {

  private static final long serialVersionUID = 1L;

  public abstract PK getId();

  //public abstract void setId(PK id);

  public BaseBean() {
    super();
  }

  public BaseBean(PK id) {
    super();
    // setId(id);
  }
  
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}