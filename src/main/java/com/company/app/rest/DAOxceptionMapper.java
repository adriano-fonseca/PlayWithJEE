package com.company.app.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.company.app.business.DAOException;

@Provider
public class DAOxceptionMapper implements ExceptionMapper<DAOException> {
	
	@Override
	public Response toResponse(DAOException ex) {
		Map<String, String> erros = new HashMap<>();
		erros.put("mensagem", ex.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(erros).build();
	}

}
