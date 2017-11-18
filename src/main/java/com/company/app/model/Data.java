package com.company.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DATA")
public class Data extends BaseBean<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "Data_SEQ", sequenceName = "ID_DATA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Data_SEQ")
	@Column(name = "ID_DATA")
	private Long idData;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "ID_DIFF", insertable = false, updatable = false)
	private Long idDiff;
	
	@Override
	public Long getId() {
		return this.idData;
	}

	public Long getIdData() {
		return idData;
	}

	public void setIdData(Long idData) {
		this.idData = idData;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getIdDiff() {
		return idDiff;
	}

	public void setIdDiff(Long idDiff) {
		this.idDiff = idDiff;
	}

}
