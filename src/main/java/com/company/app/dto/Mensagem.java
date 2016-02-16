package com.company.app.dto;

import java.io.Serializable;

public class Mensagem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensagem;

	
	public Mensagem(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
