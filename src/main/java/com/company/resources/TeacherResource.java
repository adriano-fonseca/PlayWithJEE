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
import com.company.app.model.Student;
import com.company.app.model.Teacher;
import com.company.app.rest.services.TeacherService;

@Path("/teachers")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class TeacherResource {

  @Inject
  HelperService  helperService;

  @Inject
  TeacherService teacherService;

  @GET
  @Path("{idTeacher}")
  public Response find(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idTeacher") Long idTeacher) {
    helperService.validateUser(idUser, password);
    Teacher teacher = teacherService.find(idTeacher);
    return Response.ok().entity(teacher).build();
  }
  

  @GET
  public Response find(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
    helperService.validateUser(idUser, password);
    List<Teacher> teachers = teacherService.list();
    return Response.ok().entity(teachers).build();
  }

  @DELETE
  @Path("{idTeacher}")
  public Response remove(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idTeacher") Long idTeacher) {
    helperService.validateUser(idUser, password);
    return Response.ok(teacherService.remove(idTeacher)).build();
  }

  @POST
  public Response add(Teacher teacher, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
    helperService.validateUser(idUser, password);
    teacher = teacherService.add(teacher);
    return Response.ok().entity(teacher).build();
  }

  @PUT
  @Path("{idTeacher}")
  public Response change(Teacher teacher, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idTeacher") Long idTeacher) {
    helperService.validateUser(idUser, password);
    teacher.setIdTeacher(idTeacher);
    teacher = teacherService.change(teacher);
    return Response.ok().entity(teacher).build();
  }

}
