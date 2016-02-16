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
@Table(name = "CLASS")
public class Class implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "Class_SEQ", sequenceName = "ID_CLASS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Class_SEQ")
	@Column(name = "ID_CLASS")
	private Long idClass;
	
	@Column(name = "NAME_CLASS")
	private String nameClass;
	
	@Column(name = "ID_SCHOOL_CLASS")
	private String idSchool;
	
	@Column(name = "NAME_SERIATION_CLASS")
	private String nomeSerie;
	
	@Column(name = "ID_TEACHER_CLASS")
	private Long idTeacher;
	
	@Column(name = "YEAR_CLASS")
	private String year;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TEACHER", insertable = false, updatable = false)
	private Teacher teacher;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
  private List<StudentClass> listaStudentClass = new ArrayList<StudentClass>();

  public Long getIdClass() {
    return idClass;
  }

  public void setIdClass(Long idClass) {
    this.idClass = idClass;
  }

  public String getNameClass() {
    return nameClass;
  }

  public void setNameClass(String nameClass) {
    this.nameClass = nameClass;
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

  public void setYear(String year) {
    this.year = year;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idClass == null) ? 0 : idClass.hashCode());
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
    Class other = (Class) obj;
    if (idClass == null) {
      if (other.idClass != null)
        return false;
    } else if (!idClass.equals(other.idClass))
      return false;
    return true;
  }
  
  
}
