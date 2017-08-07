package com.company.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.app.dao.HelperService;
import com.company.app.model.SchoolGroup;
import com.company.app.rest.services.SchoolGroupService;

@Path("/schoolGroups")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class SchoolGroupResource {

	@Inject
	HelperService helperService;
	
  @Inject
  SchoolGroupService schoolGroupService;

	@GET
	@Path("{idSchoolGroup}")
	public Response find(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idSchoolGroup") Long idSchoolGroup) {
		helperService.validateUser(idUser, password);
		SchoolGroup schoolGroup = schoolGroupService.find(idSchoolGroup);
		return Response.ok().entity(schoolGroup).build();
	}

	@GET
	public Response list(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
		helperService.validateUser(idUser, password);
		List<SchoolGroup> schoolGroups = schoolGroupService.list();
		return Response.ok().entity(schoolGroups).build();
	}

	@DELETE
	@Path("{idStudent}")
	public Response remove(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idStudent") Long idSchoolGroup) {
		helperService.validateUser(idUser, password);
		return Response.ok(schoolGroupService.remove(idSchoolGroup)).build();
	}

	@POST
	public Response add(SchoolGroup schoolGroup, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
		helperService.validateUser(idUser, password);
		schoolGroup = schoolGroupService.add(schoolGroup);
		return Response.ok().entity(schoolGroup).build();
	}

	@PUT
	@Path("{idStudent}")
	public Response change(SchoolGroup schoolGroup, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idStudent") Long idSchoolGroup) {
		helperService.validateUser(idUser, password);
		schoolGroup.setIdSchoolGroup(idSchoolGroup);
		schoolGroup = schoolGroupService.change(schoolGroup);
		return Response.ok().entity(schoolGroup).build();
	}

//	@GET
//	@Path("/{idSchoolGroup}/{year}")
//	public Response listClassFromTeacher(@PathParam("idTeacher") Long idTeacher, @PathParam("year") String year) {
//		// em construção...
//		return null;
//	}

}
