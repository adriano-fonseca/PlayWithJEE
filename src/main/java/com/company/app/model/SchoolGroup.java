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
@Table(name = "SCHOOLGROUP")
public class SchoolGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SchoolGroup_SEQ", sequenceName = "ID_SCHOOLGROUP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SchoolGroup_SEQ")
	@Column(name = "ID_SCHOOLGROUP")
	private Long idSchoolGroup;

	@Column(name = "NAME_SCHOOLGROUP")
	private String nameSchoolGroup;

	@Column(name = "ID_SCHOOL_SCHOOLGROUP")
	private String idSchool;

	@Column(name = "NAME_SERIATION_SCHOOLGROUP")
	private String nomeSerie;

	@Column(name = "ID_TEACHER_SCHOOLGROUP")
	private Long idTeacher;

	@Column(name = "YEAR_SCHOOLGROUP")
	private String year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TEACHER", insertable = false, updatable = false)
	private Teacher teacher;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SCHOOL", insertable = false, updatable = false)
	private School school;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private List<StudentSchoolGroup> listaStudentSchoolGroup = new ArrayList<StudentSchoolGroup>();

	public Long getIdSchoolGroup() {
		return idSchoolGroup;
	}

	public void setIdSchoolGroup(Long idSchoolGroup) {
		this.idSchoolGroup = idSchoolGroup;
	}

	public String getNameSchoolGroup() {
		return nameSchoolGroup;
	}

	public void setNameSchoolGroup(String nameSchoolGroup) {
		this.nameSchoolGroup = nameSchoolGroup;
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
		result = prime * result + ((idSchoolGroup == null) ? 0 : idSchoolGroup.hashCode());
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
		SchoolGroup other = (SchoolGroup) obj;
		if (idSchoolGroup == null) {
			if (other.idSchoolGroup != null)
				return false;
		} else if (!idSchoolGroup.equals(other.idSchoolGroup))
			return false;
		return true;
	}

}
