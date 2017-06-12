package com.company.app.rest.services;

import java.util.HashMap;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.company.app.dao.StudentSchoolGroupDAO;
import com.company.app.dao.util.PropriedadesLista;
import com.company.app.model.StudentSchoolGroup;

@Model
public class StudentSchoolGroupService {
  
	@Inject
  StudentSchoolGroupDAO studentSchoolGroupDAO;

  public StudentSchoolGroup find(Long idSchoolGroup) {
  	StudentSchoolGroup studentSchoolGroup = new StudentSchoolGroup();
  	studentSchoolGroup.setIdStudentGroup(idSchoolGroup);
    return studentSchoolGroupDAO.find(studentSchoolGroup);
  }

  public HashMap<String, String> remove(Long idStudentGroup) {
  	StudentSchoolGroup studentSchoolGroup = new StudentSchoolGroup();
  	studentSchoolGroup.setIdStudentGroup(idStudentGroup);
    return studentSchoolGroupDAO.remove(studentSchoolGroup);
  }

  public StudentSchoolGroup add(StudentSchoolGroup studentSchoolGroup) {
    return studentSchoolGroupDAO.add(studentSchoolGroup);
  }

  public StudentSchoolGroup change(StudentSchoolGroup studentSchoolGroup) {
    return studentSchoolGroupDAO.change(studentSchoolGroup);
  }
  
  public List<StudentSchoolGroup> list(){
    return studentSchoolGroupDAO.list();
  }
  
  public List<StudentSchoolGroup> listWithStudentLoaded(){
  	StudentSchoolGroup studentSchoolGroup = new StudentSchoolGroup();
  	return studentSchoolGroupDAO.listWithStudentLoaded(studentSchoolGroup, new PropriedadesLista());
  }

}
