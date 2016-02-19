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
@Table(name = "TEACHER")
public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "Teacher_SEQ", sequenceName = "ID_TEACHER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Teacher_SEQ")
	@Column(name = "ID_TEACHER")
	private Long idTeacher;

	@Column(name = "NAME_TEACHER")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	private List<Group> classList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	private List<StudentGroup> listaStudentClass = new ArrayList<StudentGroup>();

	public Long getIdTeacher() {
		return idTeacher;
	}

	public List<Group> getClassList() {
		return classList;
	}

	public void setClassList(List<Group> classList) {
		this.classList = classList;
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
}
