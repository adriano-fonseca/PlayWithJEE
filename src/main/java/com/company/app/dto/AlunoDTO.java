package com.company.app.dto;

import java.io.Serializable;

public class AlunoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idAluno;
	private Long idAlunoTurma;
	private String nome;
	
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public Long getIdAlunoTurma() {
		return idAlunoTurma;
	}
	public void setIdAlunoTurma(Long idAlunoTurma) {
		this.idAlunoTurma = idAlunoTurma;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	


	
	

}
