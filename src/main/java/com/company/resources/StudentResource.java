package com.company.resources;

import java.util.HashMap;
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
import com.company.app.rest.services.StudentService;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class StudentResource {
	
  @Inject
  HelperService  helperService;

  @Inject
  StudentService studentService;

  @GET
  @Path("{idStudent}")
  public Response find(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idStudent") Long idStudent) {
    helperService.validateUser(idUser, password);
    Student student = studentService.find(idStudent);
    return Response.ok().entity(student).build();
  }
  
  @GET
  public Response list(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
    helperService.validateUser(idUser, password);
    List<Student> students = studentService.list();
    return Response.ok().entity(students).build();
  }

  @DELETE
  @Path("{idStudent}")
  public Response remove(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idStudent") Long idStudent) {
    helperService.validateUser(idUser, password);
    return Response.ok(studentService.remove(idStudent)).build();
  }

  @POST
  public Response add(Student student, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
    helperService.validateUser(idUser, password);
    student = studentService.add(student);
    return Response.ok().entity(student).build();
  }

  @PUT
  @Path("{idStudent}")
  public Response change(Student student, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idStudent") Long idStudent) {
    helperService.validateUser(idUser, password);
    student.setIdStudent(idStudent);
    student = studentService.change(student);
    return Response.ok().entity(student).build();
  }

}
