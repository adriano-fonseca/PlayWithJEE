package com.company.app.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.company.app.dao.HelperDAO;
import com.company.app.model.Teacher;

@Path("/teachers")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class TeacherRest {
	
	@Inject
	HelperDAO helperRN;
	
	private static final Logger LOGGER = Logger.getLogger(TeacherRest.class);  
	
	@GET
	@Path("/{idTeacher}")
	public Response find(@HeaderParam("idUser") String idUser,@HeaderParam("passUser") String password,@PathParam("idTeacher") Long idTeacher){
		LOGGER.info("INICIANDO AUTENTICACAO...");
		helperRN.validaUsuario(idUser, password);
		LOGGER.info("AUTENTICACAO FINALIZADA!");
		
		LOGGER.info("INICIANDO SERVICO PROFESSORES CONSULTA...");
		Teacher professor = helperRN.find(idTeacher);
		LOGGER.info("FINALIZANDO SERVICO PROFESSORES CONSULTA...");
		return Response.ok().entity(professor).build();
	}
	
	@GET
  public Response find(){
    return Response.ok().entity("Ok").build();
  }
}
