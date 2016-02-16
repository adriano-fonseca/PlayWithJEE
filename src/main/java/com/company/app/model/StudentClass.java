package com.company.app.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

public class StudentClass {
  @Id
  @SequenceGenerator(name = "StudentClass_SEQ", sequenceName = "ID_STUDENT_CLASS_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentClass_SEQ")
  @Column(name = "ID_STUDENT_CLASS")
  private Long idStudentClass;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_STUDENT")
  private Student student;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_STUDENT")
  private Teacher teacher;

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public Long getIdStudentClass() {
    return idStudentClass;
  }

  public void setIdStudentClass(Long idStudentClass) {
    this.idStudentClass = idStudentClass;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idStudentClass == null) ? 0 : idStudentClass.hashCode());
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
    StudentClass other = (StudentClass) obj;
    if (idStudentClass == null) {
      if (other.idStudentClass != null)
        return false;
    } else if (!idStudentClass.equals(other.idStudentClass))
      return false;
    return true;
  }
  
  
  
}
