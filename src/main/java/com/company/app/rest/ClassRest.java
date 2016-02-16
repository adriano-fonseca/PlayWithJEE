package com.company.app.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.app.dao.HelperDAO;

@Path("/classes")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ClassRest {
	
	@Inject
	HelperDAO helperRN;
	
	@GET
	@Path("/{idClass}")
	public Response consulta(@PathParam("idClass") Long idClass){
		//em construção...
		return null;
	}
	
	@GET
	@Path("/{idTeacher}/{year}")
	public Response listClassFromTeacher(@PathParam("idTeacher") Long idTeacher, @PathParam("year") String year){
		//em construção...
		return null;
	}
	
}
