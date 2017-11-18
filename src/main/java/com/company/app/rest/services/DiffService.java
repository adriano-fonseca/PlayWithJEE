package com.company.app.rest.services;

import java.util.HashMap;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.company.app.dao.DataDAO;
import com.company.app.dao.DiffDAO;
import com.company.app.model.Diff;

@Model
public class DiffService {
  
  @Inject
  DiffDAO diffDAO;
  
  @Inject
  DataDAO dataDAO;

  public Diff find(Long idDiff) {
    Diff diff = new Diff();
    diff.setIdDiff(idDiff);
    return diffDAO.findByIdFetchData(diff.getId());
  }

  public HashMap<String, String> remove(Long idDiff) {
    Diff diff = new Diff();
    diff.setIdDiff(idDiff);
    return diffDAO.remove(diff);
  }

  public Diff add(Diff diff) {
    diff = diffDAO.add(diff);
	return diff;
  }

  public Diff change(Diff diff) {
    return diffDAO.change(diff);
  }
  
  public List<Diff> list(){
    return diffDAO.list(new Diff());
  }

}
