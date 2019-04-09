package cliente;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import bookle.tipos.Actividad;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

// Bien.

public class Programa {
	private static final String URL_SERVICIO = "http://localhost:8080/bookle-rest/rest/";

	public static void main(String[] args) {

		final boolean delete = false; // Para que borre el documento creado
		Client cliente = Client.create();
		final String path = "actividades";
		WebResource recurso = cliente.resource(URL_SERVICIO + path);
		
		// Invocar operaci�n: crear una actividad
		MultivaluedMap<String, String> parametros = new MultivaluedMapImpl();
		parametros.add("titulo", "Actividad 1");
		parametros.add("descripcion", "Ejemplo");
		parametros.add("profesor", "Pepe");
		parametros.add("email", "pepe@gmail.com");
		ClientResponse respuesta = recurso.method("POST", ClientResponse.class, parametros);
		System.out.println("C�digo de retorno: " + respuesta.getStatus());

		String actividadURL = respuesta.getLocation().toString();
		System.out.println("Actividad: " + actividadURL);

		// Invocar la operaci�n: consultar una actividad en formato XML
		// Se utiliza la URL obtenida en el paso anterior
		recurso = cliente.resource(actividadURL);
		Builder builder = recurso.accept(MediaType.APPLICATION_XML);
		respuesta = builder.method("GET", ClientResponse.class);
		Actividad actividad = respuesta.getEntity(Actividad.class);
		System.out.println("Titulo: " + actividad.getTitulo());

		// Invocamos updateActividad
		recurso = cliente.resource(actividadURL);
		parametros = new MultivaluedMapImpl();
		parametros.add("titulo", "Revisi�n Actividad JAXB");
		parametros.add("descripcion", "Feedback del c�digo implementado");
		parametros.add("profesor", "Marcos");
		parametros.add("email", "marcos@um.es");
		respuesta = recurso.method("PUT", ClientResponse.class, parametros);
		System.out.println("C�digo de retorno PUT: " + respuesta.getStatus());

		String parametroFecha = "26-02-1997";

		// Invocamos addDiaActividad -> a�adimos un d�a de actividad
		recurso = cliente.resource(actividadURL + "/agenda");
		parametros = new MultivaluedMapImpl();
		parametros.add("fecha", parametroFecha);
		respuesta = recurso.method("POST", ClientResponse.class, parametros);
		System.out.println("C�digo de retorno POST: " + respuesta.getStatus());

		// Volvemos a consultar el XML para comprobar que, efectivamente, tenemos el d�a
		recurso = cliente.resource(actividadURL);
		builder = recurso.accept(MediaType.APPLICATION_XML);
		respuesta = builder.method("GET", ClientResponse.class);
		actividad = respuesta.getEntity(Actividad.class);
		System.out.println("Fecha de una actividad creada: " + actividad.getAgendas().getAgenda().get(0).getFecha());

		// Invocamos removeDiaActividad -> borramos el d�a de actividad creado
		recurso = cliente.resource(actividadURL + "/agenda/" + parametroFecha);
		respuesta = recurso.method("DELETE", ClientResponse.class, parametros);
		System.out.println("C�digo de retorno DELETE: " + respuesta.getStatus());

		// Invocamos removeActividad
		if (delete) {
			recurso = cliente.resource(actividadURL);
			respuesta = recurso.method("DELETE", ClientResponse.class);
			System.out.println("C�digo de retorno DELETE: " + respuesta.getStatus());
		}
	}
}