package com.company.app.dto;

import java.io.Serializable;
import java.util.List;

public class TeacherDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idProfessor;
	private String nome;
	private String idFuncional;
	private List<SchoolGroupDTO> turmas;
	
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdFuncional() {
		return idFuncional;
	}
	public void setIdFuncional(String idFuncional) {
		this.idFuncional = idFuncional;
	}
	public List<SchoolGroupDTO> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<SchoolGroupDTO> turmas) {
		this.turmas = turmas;
	}
	
	
	

}
