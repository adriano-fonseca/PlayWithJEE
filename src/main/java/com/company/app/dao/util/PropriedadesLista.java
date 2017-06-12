package com.company.app.dao.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Define as propriedades adicionais para execução dos métodos de lista. 
 * 
 * @author mauro-flores
 *
 */
public class PropriedadesLista implements Serializable{
	private static final long serialVersionUID = 1L;
	int primeiro;
	int quantidade;
	List<Ordem> ordenacao;
	
	/**
	 * Retorna o número do primeiro registro a ser lido.
	 * @return
	 */
	public int getPrimeiro() {
		return primeiro;
	}
	
	/**
	 * Retorna a lista de propriedades utilizadas para a ordenação.
	 * @return
	 */
	public List<Ordem> getOrdenacao() {
		return ordenacao;
	}
	
	/**
	 * Define a lista de propriedades utilizadas para a ordenação. 
	 * @param ordenacao
	 */
	public void setOrdenacao(List<Ordem> ordenacao) {
		this.ordenacao = ordenacao;
	}
	
	/**
	 * Define o nro do primeiro registro a ser lido. Utilizado para 
	 * a paginação.  
	 * @param primeiro
	 */
	public void setPrimeiro(int primeiro) {
		this.primeiro = primeiro;
	}
	
	/**
	 * Adiciona uma propriedade a lista de ordenação. 
	 * @param ordem
	 */
	public void addOrdem(Ordem ordem) {
		if (ordenacao == null) {
			ordenacao = new ArrayList<Ordem>();
		}
		ordenacao.add(ordem);
	}
	
	/**
	 * Retorna a quantidade máxima de registros a serem buscados na query 
	 * @return
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Define o quantidade máxima de registros a serem buscados na query
	 * @param quantidade
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}