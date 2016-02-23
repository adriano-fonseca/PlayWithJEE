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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "TEACHER")
@JsonIgnoreProperties({ "listaStudentSchoolGroup", "schoolGroupList", "id"})
public class Teacher extends BaseBean<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "Teacher_SEQ", sequenceName = "ID_TEACHER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Teacher_SEQ")
	@Column(name = "ID_TEACHER")
	private Long idTeacher;

	@Column(name = "NAME_TEACHER")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	private List<SchoolGroup> schoolGroupList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	private List<StudentSchoolGroup> listaStudentSchoolGroup = new ArrayList<StudentSchoolGroup>();

	public Long getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<SchoolGroup> getSchoolGroupList() {
    return schoolGroupList;
  }

  public void setSchoolGroupList(List<SchoolGroup> schoolGroupList) {
    this.schoolGroupList = schoolGroupList;
  }

  public List<StudentSchoolGroup> getListaStudentSchoolGroup() {
    return listaStudentSchoolGroup;
  }

  public void setListaStudentSchoolGroup(List<StudentSchoolGroup> listaStudentSchoolGroup) {
    this.listaStudentSchoolGroup = listaStudentSchoolGroup;
  }

  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTeacher == null) ? 0 : idTeacher.hashCode());
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
		Teacher other = (Teacher) obj;
		if (idTeacher == null) {
			if (other.idTeacher != null)
				return false;
		} else if (!idTeacher.equals(other.idTeacher))
			return false;
		return true;
	}

  @Override
  public Long getId() {
    // TODO Auto-generated method stub
    return this.idTeacher;
  }
}
