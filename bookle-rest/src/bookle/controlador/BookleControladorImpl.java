package bookle.controlador;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import bookle.tipos.Actividad;
import bookle.tipos.TipoAgenda;
import bookle.tipos.TipoAgendas;
import bookle.tipos.TipoReserva;
import bookle.tipos.TipoTurno;
import bookle.tipos.TipoTurnos;

// Muy bien.



public class BookleControladorImpl implements BookleControlador {

	public final static String FOLDER = "actividades/";

	public BookleControladorImpl() {
		this.comprobarDirectorio();
	}

	private void comprobarDirectorio() {
		File folder = new File(FOLDER);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	private File getActividadFile(String id) {
		return new File(FOLDER + id + ".xml");
	}

	private File getActividadFile(Actividad actividad) {
		return this.getActividadFile(actividad.getId());
	}

	private Actividad loadActividad(String id) throws BookleException {
		try {
			JAXBContext contexto = JAXBContext.newInstance("bookle.tipos");
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			return (Actividad) unmarshaller.unmarshal(getActividadFile(id));
		} catch (Exception e) {
			throw new BookleException("Error al cargar la actividad", e);
		}
	}

	private void persistActividad(Actividad actividad) throws BookleException {
		try {
			JAXBContext contexto = JAXBContext.newInstance("bookle.tipos");
			Marshaller marshaller = contexto.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.marshal(actividad, getActividadFile(actividad));
		} catch (Exception e) {
			throw new BookleException("Error al cargar la actividad", e);
		}
	}

	private TipoAgenda getFechaActividad(Actividad actividad, Date fecha) {
		XMLGregorianCalendar fechaBuscada = Utils.createFecha(fecha);
		TipoAgendas agendas = actividad.getAgendas();

		for (TipoAgenda tipoAgenda : agendas.getAgenda()) {
			if (tipoAgenda.getFecha().equals(fechaBuscada)) {
				return tipoAgenda;
			}
		}

		return null;
	}

	@Override
	public String createActividad(String titulo, String descripcion, String profesor, String email)
			throws BookleException {

		Actividad actividad = new Actividad();
		String idActividad = Utils.createId();

		actividad.setTitulo(titulo);
		actividad.setDescripcion(descripcion);
		actividad.setProfesor(profesor);
		actividad.setEmail(email);
		actividad.setId(idActividad);
		actividad.setAgendas(new TipoAgendas());

		this.persistActividad(actividad);
		return actividad.getId();
	}

	@Override
	public void updateActividad(String id, String titulo, String descripcion, String profesor, String email)
			throws BookleException {

		Actividad actividad = this.loadActividad(id);
		actividad.setTitulo(titulo);
		actividad.setDescripcion(descripcion);
		actividad.setProfesor(profesor);
		actividad.setEmail(email);
		this.persistActividad(actividad);
	}

	@Override
	public Actividad getActividad(String id) throws BookleException {
		return this.loadActividad(id);
	}

	@Override
	public boolean removeActividad(String id) throws BookleException {
		File actividad = this.getActividadFile(id);
		return actividad.delete();
	}

	@Override
	public void addDiaActividad(String id, Date fecha, int numeroTurnos) throws BookleException {
		if (numeroTurnos <= 0) {
			throw new BookleException("El número de turnos tiene que ser mayor que 0");
		}

		Actividad actividad = this.loadActividad(id);

		if (this.getFechaActividad(actividad, fecha) != null) {
			throw new BookleException("Ya existe una actividad para ese día");
		}

		List<TipoAgenda> agendas = actividad.getAgendas().getAgenda();

		TipoAgenda agenda = new TipoAgenda();
		agenda.setFecha(Utils.createFecha(fecha));
		agenda.setTurnos(new TipoTurnos());

		List<TipoTurno> turnos = agenda.getTurnos().getTurno();
		for (int i = 0; i < numeroTurnos; i++) {
			TipoTurno turno = new TipoTurno();
			turno.setHorario("");
			turnos.add(turno);
		}

		agendas.add(agenda);
		this.persistActividad(actividad);
	}

	@Override
	public boolean removeDiaActividad(String id, Date fecha) throws BookleException {
		Actividad actividad = this.loadActividad(id);
		TipoAgenda tipoAgenda = this.getFechaActividad(actividad, fecha);

		boolean exito = actividad.getAgendas().getAgenda().remove(tipoAgenda);
		this.persistActividad(actividad);

		return exito;
	}

	@Override
	public int addTurnoActividad(String id, Date fecha) throws BookleException {
		Actividad actividad = this.loadActividad(id);
		TipoAgenda tipoAgenda = this.getFechaActividad(actividad, fecha);

		List<TipoTurno> turnos = tipoAgenda.getTurnos().getTurno();

		TipoTurno turno = new TipoTurno();
		turno.setHorario("");

		turnos.add(turno);
		this.persistActividad(actividad);

		return turnos.size() + 1;
	}

	@Override
	public void removeTurnoActividad(String id, Date fecha, int turno) throws BookleException {
		Actividad actividad = this.loadActividad(id);
		TipoAgenda tipoAgenda = this.getFechaActividad(actividad, fecha);

		List<TipoTurno> turnos = tipoAgenda.getTurnos().getTurno();

		turno--;

		if (turno < 0 || turno > turnos.size()) {
			throw new BookleException("Numero de turno incorrecto: [1 - nº turnos]");
		}

		turnos.remove(turno);
		this.persistActividad(actividad);
	}

	@Override
	public void setHorario(String id, Date fecha, int turno, String horario) throws BookleException {
		Actividad actividad = this.loadActividad(id);
		TipoAgenda tipoAgenda = this.getFechaActividad(actividad, fecha);

		List<TipoTurno> turnos = tipoAgenda.getTurnos().getTurno();

		turno--;

		if (turno < 0 || turno > turnos.size()) {
			throw new BookleException("Numero de turno incorrecto: [1 - nº turnos]");
		}

		turnos.get(turno).setHorario(horario);

		this.persistActividad(actividad);
	}

	@Override
	public String createReserva(String id, Date fecha, int turno, String alumno, String email) throws BookleException {
		Actividad actividad = this.loadActividad(id);
		TipoAgenda tipoAgenda = this.getFechaActividad(actividad, fecha);

		List<TipoTurno> turnos = tipoAgenda.getTurnos().getTurno();

		turno--;

		if (turno < 0 || turno > turnos.size()) {
			throw new BookleException("Numero de turno incorrecto: [1 - nº turnos]");
		}

		TipoTurno tipoTurno = turnos.get(turno);
		TipoReserva reserva = tipoTurno.getReserva();
		if (reserva != null) {
			throw new BookleException("Turno ya reservado");
		}

		String idReserva = Utils.createId();
		reserva = new TipoReserva();
		reserva.setAlumno(alumno);
		reserva.setEmail(email);
		reserva.setId(idReserva);

		tipoTurno.setReserva(reserva);

		this.persistActividad(actividad);
		return idReserva;
	}

	@Override
	public boolean removeReserva(String id, String idReserva) throws BookleException {
		Actividad actividad = this.loadActividad(id);

		for (TipoAgenda agenda : actividad.getAgendas().getAgenda()) {
			for (TipoTurno turno : agenda.getTurnos().getTurno()) {
				TipoReserva reserva = turno.getReserva();
				if (reserva != null && reserva.getId().equals(idReserva)) {
					turno.setReserva(null);
					this.persistActividad(actividad);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public LinkedList<Actividad> getActividades() throws BookleException {
		LinkedList<Actividad> actividades = new LinkedList<>();
		File folder = new File(FOLDER);

		for (File file : folder.listFiles()) {
			Actividad actividadEncontrada = this.loadActividad(file.getName().replace(".xml", ""));
			actividades.add(actividadEncontrada);
		}

		return actividades;
	}

}
