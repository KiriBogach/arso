package bookle.rest;

import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import bookle.controlador.BookleControlador;
import bookle.controlador.BookleControladorImpl;
import bookle.controlador.Utils;

// Bien.

@Path("actividades")
public class ServicioBookle {

	@Context
	private UriInfo uriInfo;
	private BookleControlador controlador = new BookleControladorImpl();

	@POST
	public Response createActividad(@FormParam("titulo") String titulo, @FormParam("descripcion") String descripcion,
			@FormParam("profesor") String profesor, @FormParam("email") String email) {

		String id = controlador.createActividad(titulo, descripcion, profesor, email);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(id);
		return Response.created(builder.build()).build();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getActividad(@PathParam("id") String id) {
		return Response.status(Response.Status.OK).entity(controlador.getActividad(id)).build();
	}

	@PUT
	@Path("{id}")
	public Response updateActividad(@PathParam("id") String id, @FormParam("titulo") String titulo,
			@FormParam("descripcion") String descripcion, @FormParam("profesor") String profesor,
			@FormParam("email") String email) {

		controlador.updateActividad(id, titulo, descripcion, profesor, email);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@DELETE
	@Path("{id}")
	public Response removeActividad(@PathParam("id") String id) {
		controlador.removeActividad(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST
	@Path("{id}/agenda")
	public Response addDiaActividad(@PathParam("id") String id, @FormParam("fecha") String fecha) {
		Date date = Utils.dateFromString(fecha);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(fecha);
		controlador.addDiaActividad(id, date, 1);
		return Response.created(builder.build()).build();
	}

	@DELETE
	@Path("{id}/agenda/{fecha}")
	public Response removeDiaActividad(@PathParam("id") String id, @PathParam("fecha") String fecha) {
		Date date = Utils.dateFromString(fecha);
		controlador.removeDiaActividad(id, date);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
