package com.company.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "DIFF")
@JsonIgnoreProperties({ "listaStudentSchoolGroup", "id"})
public class Diff extends BaseBean<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "Diff_SEQ", sequenceName = "ID_DATA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Diff_SEQ")
	@Column(name = "ID_DIFF")
	private Long idDiff;
	
	public Diff(Long idDiff) {
		super();
		this.idDiff = idDiff;
	}
	
	public Diff() {
		super();
	}

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ID_DIFF")
	private List<Data> listData = new ArrayList<Data>();
	
	
	@Override
	public Long getId() {
		return this.idDiff;
	}

	public Long getIdDiff() {
		return idDiff;
	}

	public void setIdDiff(Long idDiff) {
		this.idDiff = idDiff;
	}

	public List<Data> getListData() {
		return listData;
	}

	public void setListData(List<Data> listData) {
		this.listData = listData;
	}
	
}
