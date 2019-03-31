package bookle.client;

import java.util.Date;

import bookle.controlador.BookleControlador;
import bookle.controlador.Controlador;
import bookle.controlador.Utils;
import bookle.tipos.Actividad;

public class Cliente {

	public static void main(String[] args) {
		BookleControlador controlador = new Controlador();
		String id = controlador.createActividad("Revision Actividades", "Feedback del código implementado", "Marcos",
				"marcos@um.es");

		controlador.updateActividad(id, "Revisión Actividad JAXB :)", "Feedback del código implementado", "Don Marcos",
				"marcos@um.es");

		Actividad actividad = controlador.getActividad(id);
		System.out.println("Objecto Actividad: " + actividad);

		controlador.addDiaActividad(id, new Date(), 2);
		controlador.addDiaActividad(id, Utils.dateFromString("26-02-2019"), 3);
		System.out.println(controlador.removeDiaActividad(id, new Date()));

		controlador.addTurnoActividad(id, Utils.dateFromString("26-02-2019"));
		controlador.removeTurnoActividad(id, Utils.dateFromString("26-02-2019"), 1);

		controlador.createReserva(id, Utils.dateFromString("26-02-2019"), 1, "Kyryl", "kyryl.bogach@um.es");
		String reservaParaBorrar = controlador.createReserva(id, Utils.dateFromString("26-02-2019"), 2, "Borrada", "kyryl.bogach@um.es");
		controlador.removeReserva(id, reservaParaBorrar);
		
		System.out.println("Actividad '" + id + "' creada correctamente");

		System.out.println("Lista de actividades: " + controlador.getActividades());
	}

}
