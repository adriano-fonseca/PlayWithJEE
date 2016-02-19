package com.company.app.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.app.dao.HelperDAO;

@Path("/Students")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class StudentRest {
	
	@Inject
	HelperDAO helperRN;
	
	@GET
	@Path("/{id}")
	public Response find(@PathParam("id") Long idStudent){
		//em construção...
		return null;
	}
	
	@GET
	@Path("/{idClass}")
	public Response list(@PathParam("idClass") Long idClass){
		//em construção...
		return null;
	}
	
	@GET
	public Response list(@QueryParam("name") String name) {
		//em construção...
		return null;
	}
	

}
