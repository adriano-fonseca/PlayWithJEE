package com.company.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SCHOOL")
public class School implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "School_SEQ", sequenceName = "ID_SCHOOL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "School_SEQ")

	@Column(name = "ID_SCHOOL")
	private Long idSchool;

	@Column(name = "NAME_SCHOOL")
	private String nameSchool;

	@Column(name = "PHONE_SCHOOL")
	private Long phoneSchool;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	private List<Group> listaClassSchool = new ArrayList<Group>();

	public Long getIdSchool() {
		return idSchool;
	}

	public void setIdSchool(Long idSchool) {
		this.idSchool = idSchool;
	}

	public String getNameSchool() {
		return nameSchool;
	}

	public void setNameSchool(String nameSchool) {
		this.nameSchool = nameSchool;
	}

	public Long getPhoneSchool() {
		return phoneSchool;
	}

	public void setPhoneSchool(Long phoneSchool) {
		this.phoneSchool = phoneSchool;
	}

	public List<Group> getListaClassSchool() {
		return listaClassSchool;
	}

	public void setListaClassSchool(List<Group> listaClassSchool) {
		this.listaClassSchool = listaClassSchool;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSchool == null) ? 0 : idSchool.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		if (idSchool == null) {
			if (other.idSchool != null)
				return false;
		} else if (!idSchool.equals(other.idSchool))
			return false;
		return true;
	}

	
}
