package com.company.app.rest;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import com.company.app.dao.StudentDAO;
import com.company.app.model.Student;

public class StudentService {
  @Inject
  StudentDAO studentDAO;

  public Student find(Long idStudent) {
    Student student = new Student();
    student.setIdStudent(idStudent);
    return studentDAO.find(student);
  }

  public HashMap<String, String> remove(Long idStudent) {
    Student student = new Student();
    student.setIdStudent(idStudent);
    return studentDAO.remove(student);
  }

  public Student add(Student student) {
    return studentDAO.add(student);
  }

  public Student change(Student student) {
    return studentDAO.change(student);
  }
  
  public List<Student> list(){
    return studentDAO.list();
  }

}
