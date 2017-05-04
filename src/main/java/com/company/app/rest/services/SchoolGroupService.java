package com.company.app.rest.services;

import java.util.HashMap;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.company.app.dao.SchoolGroupDAO;
import com.company.app.model.SchoolGroup;

@Model
public class SchoolGroupService {
  @Inject
  SchoolGroupDAO schoolGroupDAO;

  public SchoolGroup find(Long idSchoolGroup) {
  	SchoolGroup schoolGroup = new SchoolGroup();
  	schoolGroup.setIdSchoolGroup(idSchoolGroup);
    return schoolGroupDAO.find(schoolGroup);
  }

  public HashMap<String, String> remove(Long idSchoolGroup) {
  	SchoolGroup schoolGroup = new SchoolGroup();
  	schoolGroup.setIdSchoolGroup(idSchoolGroup);
    return schoolGroupDAO.remove(schoolGroup);
  }

  public SchoolGroup add(SchoolGroup schoolGroup) {
    return schoolGroupDAO.add(schoolGroup);
  }

  public SchoolGroup change(SchoolGroup schoolGroup) {
    return schoolGroupDAO.change(schoolGroup);
  }
  
  public List<SchoolGroup> list(){
    return schoolGroupDAO.list();
  }

}
