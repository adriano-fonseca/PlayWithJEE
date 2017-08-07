package com.company.app.infra;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import com.company.app.dao.util.PropriedadesLista;
import com.company.app.dao.util.UtilDAO;
import com.company.app.exception.DAOException;
import com.company.app.exception.RNOptimisticLockException;
import com.company.app.exception.RecordNotFoundException;
import com.company.app.model.BaseBean;


/**
 * Implementa��o da RN(EJB) deve estender essa classe e invocar 
 *  seu m�todo {@link #init(EntityManager)} no {@link PostConstruct} 
 * 
 * @author tales-mattos
 *
 * @param <ED>
 */
public abstract class RNImpl<ED extends BaseBean<? extends Serializable>> implements RN<ED> {

  private final Log log = LogFactory.getLog(getClass());
  
  private EntityManager em;
  protected AppQuery<ED> BD;
    
  /**
   * Esse m�todo deve ser invocado no m�todo PostConstruct da RNImpl
   * @param em
   */
  protected void init(EntityManager em) {
    this.em = em;
    this.BD = new AppQuery<ED>(em);    
  }
  
	@Override
	public ED find(ED ed) {
	  	ED entityReturned = find(new AppQueryVO.Builder(ed).build());
	    HashMap<String, String> msg = null;
	    if (entityReturned == null) {
	      msg = new HashMap<String, String>();
	      msg.put("message", "Record not found!");
	      if (msg != null) {
	        throw new RecordNotFoundException(msg);
	      }
	    }
	    return entityReturned;
	}

	@Override
  public ED find(AppQueryVO iseQuery) {
    long i = System.currentTimeMillis();
    if (iseQuery.isCount())
    	iseQuery = iseQuery.getBuilder().count(false).build();
    List<ED> list = BD.list(iseQuery);
    long f = System.currentTimeMillis();
    if (log.isDebugEnabled()) log.debug("*** [ED find(ED ed, IseQueryVO<ED> iseQuery)] " + (f-i) + " ms.");
    if (list == null || list.isEmpty())
      return null;
    if (list.size() > 1)
      throw new NonUniqueResultException(iseQuery.getEntity().toString());
    list.set(0, hibernateProxy2Impl(list.get(0)));
    return list.get(0);
  }
  
	@Override
  public ED find(AppQueryVO.Builder iseQueryBuilder) {
  	return find(iseQueryBuilder.build());
  }
	
	@Override
	public List<ED> list(ED ed) {
		return list(new AppQueryVO.Builder(ed).build());
	}

	@Override
	public List<ED> listDetached(ED ed) {
		return list(new AppQueryVO.Builder(ed).detachedState(true).build());
	}

	@Override
	public List<ED> list(ED ed, PropriedadesLista pl) {
		return list(new AppQueryVO.Builder(ed).propList(pl).build());
	}
  
  @Override
  public List<ED> list(AppQueryVO iseQuery) {
    long i = System.currentTimeMillis();
    if (iseQuery.isCount())
    	iseQuery = iseQuery.getBuilder().count(false).build();
    List<ED> list =  BD.list(iseQuery);
    int j = 0; for (ED edItem : list) {
      list.set(j++, hibernateProxy2Impl(edItem));
    }
    long f = System.currentTimeMillis();
    if (log.isDebugEnabled()) log.debug("*** [List<ED> list(ED ed, IseQueryVO<ED> iseQuery)] executou em: " + (f-i) + " ms.");
    return list;
  }

  @Override
  public List<ED> list(AppQueryVO.Builder iseQueryBuilder) {
  	return list(iseQueryBuilder.build());
  }

  @Override
  public long count(ED ed) {
  	return count(new AppQueryVO.Builder(ed)
  																	.count(true)
  																	.fetchJoinInNotNullProperty(false)
  																	.build());
  }
  
  @Override
  public long count(AppQueryVO iseQuery) {
    long i = System.currentTimeMillis();
    if (!iseQuery.isCount())
    	iseQuery = iseQuery.getBuilder().count(true).build();
    Number count = BD.count(iseQuery);
    long f = System.currentTimeMillis();
    if (log.isDebugEnabled()) log.debug("*** [Number count(ED ed, IseQueryVO<ED> iseQuery)] executou em: " + (f-i) + " ms.");
    return count == null ? 0L : count.longValue();
  }
  
  
  @Override
  public long count(AppQueryVO.Builder iseQueryBuilder) {
  	return count(iseQueryBuilder.build());
  }
  
  @Override
  public <T extends BaseBean<? extends Serializable>> T findById(Class<T> clazz, Serializable id) {
    if (clazz == null || id == null)
      throw new IllegalArgumentException();
    T t = em.find(clazz, id);
    return t;
  }

  @SuppressWarnings("unchecked")
  protected <T extends BaseBean<? extends Serializable>> T hibernateProxy2Impl(T ed) {
    if (ed != null && HibernateProxy.class.isAssignableFrom(ed.getClass())) {
      try {
        return (T) ((HibernateProxy) ed).getHibernateLazyInitializer().getImplementation();
      } catch (Exception e) {
        return ed; 
      }      
    }
    return ed;
  }
  
  protected <T extends BaseBean<? extends Serializable>> boolean isNullOrHProxy(T ed) {
    if (ed == null || HibernateProxy.class.isAssignableFrom(ed.getClass()))
      return true;
    return false;
  }
  
	public static Number recuperarIdNumberDeEntidadeLazy(Object edProxy) {
		return (Number) recuperarIdDeEntidadeLazy(edProxy);
	}
	
	public static Serializable recuperarIdDeEntidadeLazy(Object edProxy) {
		if (edProxy == null)
			return null;
		if (!Hibernate.isInitialized(edProxy)) {
			HibernateProxy hProxy = (HibernateProxy) edProxy;
	    LazyInitializer lazyInitializer = hProxy.getHibernateLazyInitializer();
	    return lazyInitializer.getIdentifier();
		}
		return ((BaseBean<?>) edProxy).getId();
	}
	
	 public ED add(ED t) {
	    if (em.contains(t)) {
	      throw new RuntimeException("Trying add an object already menaged.");
	    }
	    em.persist(t);
	    em.flush();
	    return t;
	  }

	  public ED change(ED t) {
	    HashMap<String, String> msg = null;
	    try {
	    	ED queryBean = find(t);
	      if (queryBean == null) {
	        msg = new HashMap<String, String>();
	        msg.put("message", "Record not found!");
	        throw new RecordNotFoundException(msg);
	      }
	    } catch (DAOException e) {
	      msg.put("message", "Record not found!");
	      throw new RecordNotFoundException(msg);
	    }

	    ED managed = em.merge(t);
	    if (UtilDAO.isOpenJpa(em)) {
	      UtilDAO.forcaMergeNull(t, managed);
	    }
	    em.flush();
	    return managed;
	  }

	  @SuppressWarnings("unchecked")
	  public HashMap<String, String> remove(ED t) {
	    Object conteudoPk = t.getId();
	    HashMap<String, String> msg = null;
	    
	    try {
	    	ED entityFind = (ED) em.find(t.getClass(), conteudoPk);
	      if(entityFind==null){
	    	  msg = new HashMap<String, String>();
	    	  msg.put("message","Record not found.");
	          if (msg != null) {
	            throw new RecordNotFoundException(msg);
	          }
	      }
	      em.remove(entityFind);
	      try {
	        em.flush();
	        msg = new HashMap<String, String>();
	        msg.put("message", "Record removed!");
	      } catch (NullPointerException e) {
	        // see forum
	        // http://www.eclipse.org/forums/index.php/m/799970/#msg_799970
	        throw new RNOptimisticLockException();
	      }
	    } catch (EntityNotFoundException e) {
	        msg.put("message","Record not found.");
	      if (msg != null) {
	        throw new RecordNotFoundException(msg);
	      }
	    }
	    return msg;
	  }
	  
}