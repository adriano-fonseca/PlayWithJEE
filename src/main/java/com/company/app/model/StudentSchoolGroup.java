package com.company.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class StudentSchoolGroup extends BaseBean<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "StudentSchoolGroup_SEQ", sequenceName = "ID_STUDENT_SCHOOL_GRP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentSchoolGroup_SEQ")
	@Column(name = "ID_STUDENT_GROUP")
	private Long idStudentGroup;


	@Column(name = "ID_SCHOOLGROUP", insertable = false, updatable = false)
	private Long idSchoolGroup;

	@Column(name = "ID_STUDENT", insertable = false, updatable = false)
	private Long idStudent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STUDENT")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SCHOOLGROUP")
	private SchoolGroup schoolGroup;
	
	public StudentSchoolGroup() {
		super();
	}

	public StudentSchoolGroup(Long idStudent, Long idSchoolGroup) {
		super();
		this.student = new Student(idStudent);
		this.schoolGroup = new SchoolGroup(idSchoolGroup);
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Long getIdStudentGroup() {
		return idStudentGroup;
	}

	public void setIdStudentGroup(Long idStudentGroup) {
		this.idStudentGroup = idStudentGroup;
	}

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}

	public SchoolGroup getSchoolGroup() {
		return schoolGroup;
	}

	public void setSchoolGroup(SchoolGroup schoolGroup) {
		this.schoolGroup = schoolGroup;
	}

	public Long getIdSchoolGroup() {
		return idSchoolGroup;
	}

	public void setIdSchoolGroup(Long idSchoolGroup) {
		this.idSchoolGroup = idSchoolGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStudentGroup == null) ? 0 : idStudentGroup.hashCode());
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
		StudentSchoolGroup other = (StudentSchoolGroup) obj;
		if (idStudentGroup == null) {
			if (other.idStudentGroup != null)
				return false;
		} else if (!idStudentGroup.equals(other.idStudentGroup))
			return false;
		return true;
	}

	@Override
	public Long getId() {
		return this.idStudentGroup;
	}

}
