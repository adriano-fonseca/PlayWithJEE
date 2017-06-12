//package com.company.infra;
//
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Collection;
//
//import javax.annotation.Resource;
//import javax.ejb.Local;
//import javax.ejb.SessionContext;
//import javax.interceptor.AroundInvoke;
//import javax.interceptor.InvocationContext;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
///**
// * <pre>
// * Intercepta m�todos de EJB's: 
// *  <b>- Configurando os dados de sess�o de par�metros do tipo {@link IseED}</b>
// *  <i>E</i>
// *  <b>- Clonando par�metros quando necess�rio, ou seja:</b>
// *    > Se o m�todo existir em uma interface {@link Local} do EJB alvo E:
// *      OU se o nome, do m�todo invocado, come�ar com: "inclui", "altera", "insere", "atualiza", "update", "insert". 
// *      OU se o m�todo, ou um de seus par�mtros, estiver anotado com {@link CloneParam}
// * </pre>
// * 
// * @author tales-mattos
// * 
// * @see {@link CloneParam}
// *
// */
//public class IseInterceptor {
//
//  private final Log log = LogFactory.getLog(getClass());
//  
//  private static final String[] INITIAL_NAMES_OF_METHODS_TO_CLONE_PARAMETERS = {"inclui", "insere", "insert", "altera",  "atualiza", "update"};
//  
//  @Resource
//  private SessionContext sessionContext;
//  
//  @AroundInvoke
//  public Object intercept(InvocationContext inv) throws Exception {
//    long i = System.currentTimeMillis();
//    if (log.isDebugEnabled()) log.debug("[#] Interceptando m�todo: [" + inv.getMethod() + "]");
//    if (isInvokedFromMB()) {
//      Class<?> interfaceReference = null;
//      try {
//        interfaceReference = this.sessionContext.getInvokedBusinessInterface();
//      } catch (IllegalStateException ise) {
//        log.warn("[x] EJB n�o foi chamado por meio de uma interface de neg�cio.", ise);
//      }
//      configureSessionData(inv);
//      if (interfaceReference != null)
//        cloneParameters(inv, interfaceReference);
//    }
//    long f = System.currentTimeMillis();
//    if (log.isDebugEnabled()) log.debug("[#] IseInterceptor executou em " + (f-i) + " ms.");
//    
//    try {
//      return inv.proceed();
//    } catch (Exception ex) {
//      throw ex;
//    }
//  }
//  
//  private boolean isInvokedFromMB() {
//    try {
//      final StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//      if (stackTraceElements == null) 
//        return false;
//      int i = 0; 
//      for (StackTraceElement stackTraceElement : stackTraceElements) {
//        if ( stackTraceElement.getClassName().startsWith("com.procergs.ise.mb.") || stackTraceElement.getClassName().equals("com.procergs.arqjava.faces.CrudMB") ) {
//            if (log.isDebugEnabled()) log.debug("[#] (StackTraceElemen[" + i + "]) Invocado de um MB: " + stackTraceElement);
//            return true;
//        } else if (stackTraceElement.getClassName().endsWith("RNImpl")) {
//          if (log.isDebugEnabled()) log.debug("[#] (StackTraceElemen[" + i + "]) Invocado de uma RNImpl: " + stackTraceElement);
//          return false;
//        }
//        i++;
//      }
//    } catch (Exception e) {
//      log.warn("[x] Tentativa de recuperar a classe que invocou o m�todo interceptado falhou.", e);
//    }
//    if (log.isDebugEnabled()) log.debug("[#] N�o encontrou a classe que invocou o m�todo. N�o est� no pacote no pacote com.procergs.ise.mb e n�o � um EJB terminado em RNImpl ");
//    return false;
//  }
//
//  private void configureSessionData(InvocationContext inv) {
//    try {
//      Object[] parameters = inv.getParameters();
//      if (parameters != null) {
//        for (Object param : parameters) {
//          if (param instanceof DadosSessao<?>) {
//            @SuppressWarnings("unchecked")
//            DadosSessao<SessionED> ed = (DadosSessao<SessionED>) param;
//            if (ed.getDadosSessao() == null) {
//              if (log.isDebugEnabled()) log.debug("[#] Configurando dados da sess�o");
//              ed.setDadosSessao((SessionED) JSFUtil.recuperaObjetoSessao("DadosSessao"));
//            }
//          }
//        }
//      } 
//    } catch (Exception e) {
//      log.warn("[x] Tentativa de recuperar os dados da sess�o falhou.", e);
//    }      
//  }
//  
//  private void cloneParameters(InvocationContext inv, Class<?> interfaceReference) {
//    try {
//      if (interfaceReference.isAnnotationPresent(Local.class)) {
//        if (log.isDebugEnabled()) log.debug("[#] " + interfaceReference + " � uma interface @Local.");
//        Object[] params = inv.getParameters();
//        if (params != null) {
//          String methodName = inv.getMethod().getName();
//          boolean hitIniatialName = isHitMethodNameToClone(methodName);
//          Method methodLocal = retrieveMethodInIfc(inv.getMethod(), interfaceReference);
//          if (methodLocal != null) {
//            Object[] paramsClone = buildClonedParameters(methodLocal, params, hitIniatialName);
//            inv.setParameters(paramsClone);
//          }
//        }
//      } else {
//        if (log.isDebugEnabled()) log.debug("[#] " + interfaceReference + " N�O � uma interface @Local.");
//      }
//    } catch (Exception e) {
//      log.warn("[x] Tentativa de clonar par�metros falhou.", e);
//    }     
//  }
//  
//  private boolean isHitMethodNameToClone(String methodName) {
//    for (String initialName : INITIAL_NAMES_OF_METHODS_TO_CLONE_PARAMETERS) {
//      if (methodName.startsWith(initialName)) {
//        return true;
//      }
//    }
//    return false;
//  }
//  
//  private Method retrieveMethodInIfc(Method methodRNImpl, Class<?> clazzLocalIfc) {
//    for(Method methodInLocalIfc : clazzLocalIfc.getDeclaredMethods()) {
//      if (isSameMethod(methodInLocalIfc, methodRNImpl)) {
//        return methodInLocalIfc;
//      }
//    }
//    return null;
//  }
//  
//  private boolean isSameMethod(Method methodInIfc, Method methodInImpl) {
//    if (methodInIfc.getName().equals(methodInImpl.getName())) {
//      final Class<?>[] paramsIfc = methodInIfc.getParameterTypes();
//      final Class<?>[] paramsImpl = methodInImpl.getParameterTypes();
//      if (paramsIfc.length == paramsImpl.length) {
//        for (int i = 0; i < paramsIfc.length; i++) {
//          if (paramsIfc[i] != paramsImpl[i]) {
//            if (log.isDebugEnabled()) log.debug("[#] M�todos [" + methodInIfc + " != " + methodInImpl + "] n�o possuem os mesmos par�metros. � uma sobrecarga.");
//            return false;
//          }
//        }
//        return true;
//      }
//    }
//    return false;
//  }
//  
//  private Object[] buildClonedParameters(Method method, Object[] parameters, boolean hitIniatialName) {
//    if (parameters != null && parameters.length > 0) {
//      Object[] paramsClone = new Object[parameters.length];
//      CloneParam cloneParamAnnotationInParam = null;
//      CloneType cloneType = CloneType.SHALLOW;
//      boolean cloneCollection = false;
//      boolean executeCloning = false;
//      for (int i = 0 ; i < parameters.length ; i++) {
//        if (parameters[i] != null)  {
//          cloneParamAnnotationInParam = null;
//          try {
//            Annotation[] annotationsInParameter = method.getParameterAnnotations()[i];
//            for (Annotation annotationInParam : annotationsInParameter) {
//              if (annotationInParam instanceof CloneParam) {
//                cloneParamAnnotationInParam = (CloneParam) annotationInParam;
//                break;
//              }
//            }          
//          } catch (Exception e) {
//            log.warn(e);
//          }
//          cloneType = CloneType.SHALLOW;
//          cloneCollection = false;
//          executeCloning = false;
//          if (cloneParamAnnotationInParam != null) {
//            cloneType = cloneParamAnnotationInParam.copy();
//            cloneCollection = true;
//            executeCloning = true;
//          } else if (method.isAnnotationPresent(CloneParam.class)) {
//            cloneType = method.getAnnotation(CloneParam.class).copy();
//            cloneCollection = method.getAnnotation(CloneParam.class).cloneCollection();
//            executeCloning = true;
//          } else if (hitIniatialName) {
//            cloneType = CloneType.SHALLOW;
//            cloneCollection = false;
//            executeCloning = true;
//          }
//          if (executeCloning) {
//            if (CloneType.DEEP.equals(cloneType)) log.info("Executando clonagem DEEP no par�metro do m�todo: " + method.getDeclaringClass().getSimpleName() + "#" + method.getName());
//            if (log.isDebugEnabled()) log.debug("[#] Executando clonagem de par�metro do m�todo: " + method.getDeclaringClass().getSimpleName() + "#" + method.getName());
//            paramsClone[i] = executeCloning(parameters[i], cloneType, cloneCollection); 
//          } else {
//            paramsClone[i] = parameters[i];            
//          }
//        } else {
//          paramsClone[i] = parameters[i];
//        }
//      }      
//      return paramsClone;
//    } else {
//      return parameters;
//    }
//  }
//  
//  private Object executeCloning(Object obj, CloneType cloneType, boolean cloneCollection) {
//    if (obj == null)
//      return obj;
//    if (obj instanceof Collection<?>) {
//      if (!cloneCollection)
//        return obj;
//      Collection<?> oldCollection = (Collection<?>) obj;
//      if (!oldCollection.isEmpty()) {
//        Collection<Object> newCollection = new ArrayList<Object>();
//        if (CloneType.DEEP.equals(cloneType)) log.warn("[!] Clonando uma cole��o com " + oldCollection.size() + " objetos, no modo deep == true.");
//        if (log.isDebugEnabled()) log.debug("[#] Clonando uma cole��o com " + oldCollection.size() + " objetos.");
//        for (Object oldObj : oldCollection) {
//          newCollection.add(processClone(oldObj, cloneType));
//        }
//        return newCollection;        
//      } else {
//        return obj;        
//      }
//    } else {
//      return processClone(obj, cloneType);
//    }
//  }
//  
//  private Object processClone(Object obj, CloneType cloneType) {
//    if (obj == null || !(obj instanceof Serializable))
//      return obj;
//    try {
//      switch (cloneType) {
//        case DEEP:
//          if (log.isDebugEnabled()) log.debug("[#] DEEP COPY: " + obj.getClass() + " (" + obj + ")");
//          return deepCopy(obj);          
//        case SHALLOW:
//          if (obj instanceof IseED<?>) {
//            @SuppressWarnings("unchecked")
//            IseED<Serializable> ed = (IseED<Serializable>) obj;
//            if (log.isDebugEnabled()) log.debug("[#] SHALLOW COPY: " + ed.getClass() + " (" + ed + ")");
//            return ed.clone();
//          } else {
//            if (log.isDebugEnabled()) {
//              log.warn("[!] O par�metro " + obj.getClass() + " (" + obj + ")" + " n�o foi clonado porque foi solicitada uma c�pia rasa de uma inst�ncia que n�o corresponde a com.procergs.ise.ed.IseED.");
//              log.warn("[!] N�o � poss�vel acessar o m�todo #clone sem antes fazer cast de java.lang.Object para uma inst�ncia conhecida que implemente a interface java.lang.Cloneable");              
//            }
//            return obj;
//          }
//        default: return obj;
//      }
//    } catch (Exception e) {
//      if (log.isDebugEnabled()) log.warn("[x] Erro ao executar a clonagem. (-IseInterceptor#processClone)", e);
//      return obj;
//    }
//  }
//
//  private Object deepCopy(Object obj) {
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    try (ObjectOutputStream oos = new ObjectOutputStream(baos);) {
//      oos.writeObject(obj);
//      oos.flush();
//      try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));) {
//        return ois.readObject();        
//      }
//    } catch (Exception ex) {
//      throw new RuntimeException("Exce��o ao executar c�pia profunda do objeto: " + obj, ex);
//    }
//  }
//}