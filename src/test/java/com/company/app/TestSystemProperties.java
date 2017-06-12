package com.company.app;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class TestSystemProperties {

  @PostConstruct
  public void init(){
    System.setProperty("property.test", "teste");
  }
  
  @PreDestroy
  public void tearDown(){
    System.clearProperty("property.test");
  }
}
