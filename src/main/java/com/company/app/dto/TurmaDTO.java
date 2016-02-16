package com.company.app.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class TurmaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idTurma;
	private String nome;
	private Long idEscola;
	private String nomeEscola;
	private String nomeCalendario;
	private Calendar dtInicioCalendario;
	private Calendar dtFimCalendario;
	private String nomeSerie;
	private Long idProfessor;
	private List<AlunoDTO> alunos;
	private String nomeCurso;
	
	
	public Long getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getIdEscola() {
		return idEscola;
	}
	public void setIdEscola(Long idEscola) {
		this.idEscola = idEscola;
	}
	public String getNomeEscola() {
		return nomeEscola;
	}
	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}
	public String getNomeCalendario() {
		return nomeCalendario;
	}
	public void setNomeCalendario(String nomeCalendario) {
		this.nomeCalendario = nomeCalendario;
	}
	public Calendar getDtInicioCalendario() {
		return dtInicioCalendario;
	}
	public void setDtInicioCalendario(Calendar dtInicioCalendario) {
		this.dtInicioCalendario = dtInicioCalendario;
	}
	public Calendar getDtFimCalendario() {
		return dtFimCalendario;
	}
	public void setDtFimCalendario(Calendar dtFimCalendario) {
		this.dtFimCalendario = dtFimCalendario;
	}
	public String getNomeSerie() {
		return nomeSerie;
	}
	public void setNomeSerie(String nomeSerie) {
		this.nomeSerie = nomeSerie;
	}
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public List<AlunoDTO> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<AlunoDTO> alunos) {
		this.alunos = alunos;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	
	
	
	

}
