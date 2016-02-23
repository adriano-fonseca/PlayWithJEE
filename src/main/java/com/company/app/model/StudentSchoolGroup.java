package com.company.app.model;

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
public class StudentSchoolGroup {
  @Id
  @SequenceGenerator(name = "StudentGroup_SEQ", sequenceName = "ID_STUDENT_GROUP_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentGroup_SEQ")
  @Column(name = "ID_STUDENT_GROUP")
  private Long idStudentGroup;
  
  @Column(name = "ID_STUDENT")
  private Long idStudent;
  
  @Column(name = "ID_CLASS")
  private Long idClass;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_STUDENT", insertable = false, updatable = false)
  private Book student;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_TEACHER", insertable = false, updatable = false)
  private Teacher teacher;

  public Book getStudent() {
    return student;
  }

  public void setStudent(Book student) {
    this.student = student;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
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

public Long getIdClass() {
	return idClass;
}

public void setIdClass(Long idClass) {
	this.idClass = idClass;
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
  
  
  
}
