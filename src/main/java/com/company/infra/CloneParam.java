package com.company.infra;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

import javax.ejb.Local;

/**
 * <pre>
 *  Anote o m�todo ou par�metro da interface {@link Local} do EJB.
 *  Com essa anota��o, o(s) par�metro(s) ser�(�o) clonado(s) antes da execu��o do m�todo.
 *  Use essa anota��o em m�todos com par�metros que sofrer�o altera��es no banco de dados.
 *  Caso a transa��o execute <i>rollback</i> ou o EJB lance exce��o, o objeto passado por par�metro n�o ficar� 
 *    'sujo' no *MB, ter� seu estado original preservado.
 * 
 * <i> * O objeto a ser clonado, no modo SHALLOW, deve ser uma inst�ncia de {@link IseED}, do contr�rio n�o ser� clonado.</i>
 * <i> * O objeto a ser clonado deve implementar a interface {@link Serializable}.</i>
 * <i> * A anota��o no par�metro sobrep�em a anota��o no m�todo.</i></b>
 * <i> * M�todos cujo nome inicia com "inclui", "altera", "insere", "atualiza", "update" ou "insert" 
 *              ter�o seus par�metros, do tipo IseED, clonados automaticamente no modo SHALLOW (sem necessitar explicitar essa anota��o).</i>
 * </pre>
 * 
 * 
 * @author tales-mattos
 * 
 * @see IseInterceptor
 * @see CloneType
 */
@Target({ METHOD , PARAMETER })
@Retention(RUNTIME)
public @interface CloneParam {
  
  /**
   * <i>O padr�o � <b>false.</b></i>
   * <br>A modo <i>CloneType.DEEP</i> pode se tornar demorado se o objeto for 'pesado'.
   * <br>Use <i>CloneType.DEEP</i> em par�metros que sofrer�o altera��es em n�veis inferiores ao primeiro (Ex.: alunoCursoED.getAlunoED.setInPassivo("N")).<br><br>
   * @return Orienta��o para o tipo de clonagem.
   */
  public abstract CloneType copy() default CloneType.SHALLOW;
  
  /**
   * <pre>
   *    Use no n�vel do m�todo para indicar que deseja que os 
   * par�metros do tipo {@link Collection} tenham seus elementos clonados.
   *    Caso {@link CloneParam} esteja especificamente referenciando o par�metro, 
   * do tipo {@link Collection}, n�o ser� necess�rio explicitar 'cloneCollection = true'
   * </pre>
   * @return Orienta��o para permitir clonagem de par�metros do tipo {@link Collection}
   */
  public abstract boolean cloneCollection() default false;
  
}
