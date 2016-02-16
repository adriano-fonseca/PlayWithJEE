package com.company.app.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String identificacao;
	private String senha;
	private String siglaSistema;
	
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSiglaSistema() {
		return siglaSistema;
	}
	public void setSiglaSistema(String siglaSistema) {
		this.siglaSistema = siglaSistema;
	}
	
	
}
