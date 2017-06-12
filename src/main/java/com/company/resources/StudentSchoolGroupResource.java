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
import com.company.app.model.StudentSchoolGroup;
import com.company.app.rest.services.StudentSchoolGroupService;

@Path("/schooStudentSchoolGroup")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class StudentSchoolGroupResource {

	@Inject
	HelperService helperService;
	
  @Inject
  StudentSchoolGroupService studentSchoolGroupService;

	@GET
	@Path("{idStudentSchoolGroup}")
	public Response find(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idSchoolGroup") Long idSchoolGroup) {
		helperService.validateUser(idUser, password);
		StudentSchoolGroup schoolGroup = studentSchoolGroupService.find(idSchoolGroup);
		return Response.ok().entity(schoolGroup).build();
	}

	@GET
	public Response list(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
		helperService.validateUser(idUser, password);
		List<StudentSchoolGroup> studentSchoolGroups = studentSchoolGroupService.listWithStudentLoaded();
		return Response.ok().entity(studentSchoolGroups).build();
	}

	@DELETE
	@Path("{idStudentSchoolGroup}")
	public Response remove(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idStudent") Long idSchoolGroup) {
		helperService.validateUser(idUser, password);
		return Response.ok(studentSchoolGroupService.remove(idSchoolGroup)).build();
	}

	@POST
	public Response add(StudentSchoolGroup studentSchoolGroup, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
		helperService.validateUser(idUser, password);
		StudentSchoolGroup ed = new StudentSchoolGroup(studentSchoolGroup.getIdStudent(), studentSchoolGroup.getIdSchoolGroup());
		ed = studentSchoolGroupService.add(ed);
		return Response.ok().entity(studentSchoolGroup).build();
	}

	@PUT
	@Path("{idStudentSchoolGroup}")
	public Response change(StudentSchoolGroup studentSchoolGroup, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idStudentSchoolGroup") Long idStudentGroup) {
		helperService.validateUser(idUser, password);
		studentSchoolGroup.setIdStudentGroup(idStudentGroup);
		studentSchoolGroup = studentSchoolGroupService.change(studentSchoolGroup);
		return Response.ok().entity(studentSchoolGroup).build();
	}

//	@GET
//	@Path("/{idSchoolGroup}/{year}")
//	public Response listClassFromTeacher(@PathParam("idTeacher") Long idTeacher, @PathParam("year") String year) {
//		// em construção...
//		return null;
//	}

}
