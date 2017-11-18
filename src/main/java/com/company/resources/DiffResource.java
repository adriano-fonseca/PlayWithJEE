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

import com.company.app.model.Diff;
import com.company.app.rest.services.DiffService;

@Path("diff/")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class DiffResource {
	
  @Inject
  DiffService diffService;

  @GET
  @Path("{idDiff}")
  public Response find(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idDiff") Long idDiff) {
    Diff diff = diffService.find(idDiff);
    return Response.ok().entity(diff).build();
  }
  
  @GET
  public Response list(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
    List<Diff> diffs = diffService.list();
    return Response.ok().entity(diffs).build();
  }

  @DELETE
  @Path("{idDiff}")
  public Response remove(@HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idDiff") Long idDiff) {
    return Response.ok(diffService.remove(idDiff)).build();
  }

  @POST
  @Path("{idDiff}/left")
  public Response addLeft(Diff diff, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
    diff = diffService.add(diff);
    return Response.ok().entity(diff).build();
  }
  
  @POST
  @Path("{idDiff}/right")
  public Response addRight(Diff diff, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password) {
    diff = diffService.add(diff);
    return Response.ok().entity(diff).build();
  }

  @PUT
  @Path("{idDiff}")
  public Response change(Diff diff, @HeaderParam("idUser") String idUser, @HeaderParam("passUser") String password, @PathParam("idDiff") Long idDiff) {
    diff.setIdDiff(idDiff);
    diff = diffService.change(diff);
    return Response.ok().entity(diff).build();
  }

}