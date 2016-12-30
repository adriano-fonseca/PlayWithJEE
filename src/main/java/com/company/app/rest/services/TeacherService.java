package com.company.app.rest.services;

import java.util.HashMap;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.company.app.dao.TeacherDAO;
import com.company.app.model.Teacher;

@Model
public class TeacherService {
  @Inject
  TeacherDAO teacherDAO;

  public Teacher find(Long idTeacher) {
    Teacher teacher = new Teacher();
    teacher.setIdTeacher(idTeacher);
    return teacherDAO.find(teacher);
  }

  public HashMap<String, String> remove(Long idTeacher) {
    Teacher teacher = new Teacher();
    teacher.setIdTeacher(idTeacher);
    return teacherDAO.remove(teacher);
  }

  public Teacher add(Teacher teacher) {
    return teacherDAO.add(teacher);
  }

  public Teacher change(Teacher teacher) {
    return teacherDAO.change(teacher);
  }
  
  public List<Teacher> list(){
    return teacherDAO.list();
  }

}
