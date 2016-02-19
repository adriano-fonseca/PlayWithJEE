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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP")
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "Group_SEQ", sequenceName = "ID_GROUP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Group_SEQ")
	@Column(name = "ID_GROUP")
	private Long idGroup;

	@Column(name = "NAME_GROUP")
	private String nameGroup;

	@Column(name = "ID_SCHOOL_GROUP")
	private String idSchool;

	@Column(name = "NAME_SERIATION_GROUP")
	private String nomeSerie;

	@Column(name = "ID_TEACHER_GROUP")
	private Long idTeacher;

	@Column(name = "YEAR_GROUP")
	private String year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TEACHER", insertable = false, updatable = false)
	private Teacher teacher;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SCHOOL", insertable = false, updatable = false)
	private School school;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private List<StudentGroup> listaStudentGroup = new ArrayList<StudentGroup>();

	public Long getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	public String getIdSchool() {
		return idSchool;
	}

	public void setIdSchool(String idSchool) {
		this.idSchool = idSchool;
	}

	public String getNomeSerie() {
		return nomeSerie;
	}

	public void setNomeSerie(String nomeSerie) {
		this.nomeSerie = nomeSerie;
	}

	public Long getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getYear() {
		return year;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGroup == null) ? 0 : idGroup.hashCode());
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
		Group other = (Group) obj;
		if (idGroup == null) {
			if (other.idGroup != null)
				return false;
		} else if (!idGroup.equals(other.idGroup))
			return false;
		return true;
	}

}
