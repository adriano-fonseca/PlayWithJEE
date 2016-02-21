package com.company.app.rest;

import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.app.dao.HelperDAO;

@Path("/Students")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class StudentRest {
	
	@Inject
	HelperDAO helperDAO;
	
	@GET
	@Path("/{id}")
	public Response find(@PathParam("id") Long idStudent){
//		TeacherDTO teacherDTO = helperDAO.consultaProfessor(1L);
	  HashMap<String, String> map = new HashMap<String, String>();
		return  Response.ok(map).build();
	}
}
