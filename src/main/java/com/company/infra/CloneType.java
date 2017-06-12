package com.company.infra;

/**
 * 
 * Orienta��o para o tipo de clonagem
 * 
 * @author tales-mattos
 *  
 * @see CloneParam
 * @see IseInterceptor
 */
public enum CloneType {
  
  /**
   * (Shallow Copy): Clona apenas os atributos primitivos/Wrappers, Date, Calendar e Strigs no primeiro n�vel do objeto. 
   * C�pia rasa.
   */
  SHALLOW,
  
  /**
   * (Deep Copy): Clona o objeto por completo (Serealiza e deserealiza). 
   * C�pia profunda.
   */
  DEEP
}
