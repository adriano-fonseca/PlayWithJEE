package com.company.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "STUDENT")
@JsonIgnoreProperties({ "listaStudentClass", "id"})
public class Student extends BaseBean<Long>
implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "Student_SEQ", sequenceName = "ID_STUDENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Student_SEQ")

	@Column(name = "ID_STUDENT")
	private Long idStudent;

	@Column(name = "NAME_STUDENT")
	private String nameStudent;

	@Temporal(TemporalType.DATE)
	private Calendar birthDayStudent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private List<StudentSchoolGroup> listaStudentClass = new ArrayList<StudentSchoolGroup>();

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}

	public String getNameStudent() {
		return nameStudent;
	}

	public void setNameStudent(String nameStudent) {
		this.nameStudent = nameStudent;
	}

	public Calendar getBirthDayStudent() {
		return birthDayStudent;
	}

	public void setBirthDayStudent(Calendar birthDayStudent) {
		this.birthDayStudent = birthDayStudent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStudent == null) ? 0 : idStudent.hashCode());
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
		Student other = (Student) obj;
		if (idStudent == null) {
			if (other.idStudent != null)
				return false;
		} else if (!idStudent.equals(other.idStudent))
			return false;
		return true;
	}

  @Override
  public Long getId() {
    // TODO Auto-generated method stub
    return this.idStudent;
  }

  @Override
  public String toString() {
    return "Student [idStudent=" + idStudent + ", nameStudent=" + nameStudent + ", birthDayStudent=" + birthDayStudent + "]";
  }
  
}
