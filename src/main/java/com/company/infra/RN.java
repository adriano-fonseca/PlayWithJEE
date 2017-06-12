package com.company.infra;

import java.io.Serializable;
import java.util.List;

import com.company.app.dao.util.PropriedadesLista;
import com.company.app.model.BaseBean;

/**
 * Interface da RN deve estender essa interface.
 * Implementa��o da RN deve estender IseRNImpl.
 * 
 * @author tales-mattos
 *
 * @param <ED>
 */
public abstract interface RN<ED extends BaseBean<? extends Serializable>> {
  
	
	/**
   * 
   * @param clazz
   * @param id
   * @return
   */
  public abstract <T extends BaseBean<? extends Serializable>> T findById(Class<T> clazz, Serializable id);
	
  /**
   * Monta uma consulta a partir dor atributos do ED e do objeto iseQueryVO.
   * 
   * @param ed
   * @return objeto
   */
  public abstract ED find(ED ed);

  /**
   * Monta uma consulta a partir dor atributos do ED e do objeto iseQueryVO.
   * 
   * @param ed
   * @param iseQuery
   * @return
   */
  public abstract ED find(IseQueryVO iseQuery);
  
  /**
   * 
   * @param iseQueryBuilder
   * @return
   */
  public abstract ED find(IseQueryVO.Builder iseQueryBuilder);
  
  /**
   * Monta uma consulta a partir dor atributos do ED.
   * 
   * @param ed
   * @return lista de objetos
   */
  public abstract List<ED> list(ED ed);

  /**
   * 
   * @param ed
   * @return
   */
  public abstract List<ED> listDetached(ED ed);

  /**
   * 
   * @param ed
   * @param pl
   * @return
   */
  public abstract List<ED> list(ED ed, PropriedadesLista pl);

  /**
   * Monta uma consulta a partir do ed informado e do objeto iseQueryVO
   * 
   * @param ed
   * @param iseQuery
   * @return lista de objetos
   */
  public abstract List<ED> list(IseQueryVO iseQuery);
 
  /**
   * Monta uma consulta a partir do ed informado e do objeto iseQueryVO
   * 
   * @param ed
   * @param iseQuery
   * @return lista de objetos
   */
  public abstract List<ED> list(IseQueryVO.Builder iseQueryBuilder);
  
  /**
   * Monta uma consulta, a partir do ED informado, para obter uma contagem
   * 
   * @param ed
   * @return count
   */
  public abstract long count(ED ed);

  /**
   * Monta uma consulta a partir do ed informado e do objeto iseQueryVO para obter uma contagem
   * 
   * @param ed
   * @param iseQuery
   * @return count
   */
  public abstract long count(IseQueryVO iseQuery);
  
  /**
   * 
   * @param iseQueryBuilder
   * @return
   */
  public abstract long count(IseQueryVO.Builder iseQueryBuilder);
  
}
