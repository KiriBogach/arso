package bookle.controlador;

import java.util.Date;
import java.util.LinkedList;

import bookle.tipos.Actividad;

// Controlador Caso de uso

/*
 * Todos los m�todos declaran que pueden lanzar la excepci�n BookleException.
 * Esta excepci�n es "runtime". 
 * Cuando se desarrolle el servicio web, no ser� necesario tratarla
 */

public interface BookleControlador {

	/** 
	 * M�todo de creaci�n de una actividad.
	 * Los par�metros email y descripcion son opcionales (aceptan valor nulo).
	 * El m�todo retorna el id de la nueva actividad
	 */
	String createActividad(String titulo, String descripcion, String profesor, String email) throws BookleException;
	
	/**
	 * M�todo de actualizaci�n de la actividad.
	 * En relaci�n al m�todo de creaci�n, a�ade un primer par�metro con el id de la actividad.
	 */
	void updateActividad(String id, String titulo, String descripcion, String profesor, String email) throws BookleException;
	
	/**
	 * Recupera la informaci�n de una actividad utilizando el identificador. 	
	 */
	Actividad getActividad(String id)  throws BookleException;
	
	/**
	 * Elimina una actividad utilizando el identificador.
	 */
	boolean removeActividad(String id)  throws BookleException;
	
	/**
	 * Esta operaci�n a�ade un d�a a una actividad.
	 * El d�a de actividad est� identificado por la fecha.
	 * Al menos debe establecerse un turno.
	 */
	void addDiaActividad(String id, Date fecha, int turnos)  throws BookleException;
	
	/**
	 * Elimina un d�a de una actividad.
	 * El id de la actividad y la fecha identifican el d�a.
	 */
	boolean removeDiaActividad(String id, Date fecha)  throws BookleException;
	
	/**
	 * A�ade un nuevo turno a un d�a de una actividad.
	 * Los par�metros id y fecha identifican el d�a.
	 * Retorna el n�mero de turno. El primer turno es el 1.
	 */
	int addTurnoActividad(String id, Date fecha) throws BookleException;
	
	/**
	 * Elimina un turno de un d�a de la actividad.
	 * El primer turno es el 1.
	 */
	void removeTurnoActividad(String id, Date fecha, int turno) throws BookleException;
	
	/**
	 * Establece la franja horaria de un turno de una actividad.
	 * El primer turno de un d�a de actividad es el 1.
	 */
	void setHorario(String idActividad, Date fecha, int indice, String horario) throws BookleException;
	
	/**
	 * Realiza la reserva de un turno de la actividad.
	 * Retorna el identificador de la reserva.
	 */
	String createReserva(String idActividad, Date fecha, int indice, String alumno, String email) throws BookleException;
	
	/**
	 * Elimina una reserva de una actividad. 
	 */
	boolean removeReserva(String idActividad, String ticket) throws BookleException;
	
	/**
	 * M�todo de consulta de todas las actividades.	
	 */
	LinkedList<Actividad> getActividades() throws BookleException;
}
