package jaxb.ejercicio12;

import java.io.File;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import calificaciones.Calificaciones;
import calificaciones.TipoCalificacion;
import calificaciones.TipoConvocatoria;
import calificaciones.TipoDiligencia;

public class ConstructorCalificaciones {
	public static void main(String[] args) throws Exception {
		final String FICHERO_SALIDA = "xml/ejercicio3/calificaciones-creado-jaxb.xml";

		Calificaciones calificaciones = new Calificaciones();

		// Construimos el objeto
		calificaciones.setConvocatoria(TipoConvocatoria.FEBRERO);
		calificaciones.setCurso(BigInteger.valueOf(2018));
		calificaciones.setAsignatura("1092");

		List<TipoCalificacion> listaCalificaciones = calificaciones.getCalificacion();
		List<TipoDiligencia> listaDiligencias = calificaciones.getDiligencia();

		TipoCalificacion calificacion1 = new TipoCalificacion();
		calificacion1.setNif("23322156M");
		calificacion1.setNota(10);

		TipoCalificacion calificacion2 = new TipoCalificacion();
		calificacion2.setNif("13322156M");
		calificacion2.setNota(8);
		calificacion2.setNombre("Pepe");

		TipoDiligencia diligencia = new TipoDiligencia();
		diligencia.setNif("13322156M");
		diligencia.setNota(8);
		GregorianCalendar calendar = new GregorianCalendar(2019, 02, 12);
		XMLGregorianCalendar fechaDiligencia = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		diligencia.setFecha(fechaDiligencia);

		// Asignamos las calificaciones y la diligencia creada
		listaCalificaciones.add(calificacion1);
		listaCalificaciones.add(calificacion2);
		listaDiligencias.add(diligencia);

		// Guardamos en disco
		JAXBContext contexto = JAXBContext.newInstance("calificaciones");

		Marshaller marshaller = contexto.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.setProperty("jaxb.schemaLocation", "http://www.example.org/calificaciones ejercicio3.xsd");

		marshaller.marshal(calificaciones, new File(FICHERO_SALIDA));

		System.out.println("Archivo '" + FICHERO_SALIDA + "', generado correctamente.");
		System.out.println("Fin.");
	}
}
