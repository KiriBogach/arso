package jaxb.ejercicio11;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import calificaciones.Calificaciones;
import calificaciones.TipoCalificacion;
import calificaciones.TipoDiligencia;

public class Ejercicio7 {

	public static void main(String[] args) throws Exception {

		final String FICHERO_ENTRADA = "xml/ejercicio3/ejercicio3_diligencias.xml";
		final String FICHERO_SALIDA = "xml/ejercicio3/calificaciones-modificado-jaxb.xml";
		final double NOTA_MAXIMA = 10.0;
		final double INCREMENTO = 0.5;
		
		JAXBContext contexto = JAXBContext.newInstance("calificaciones");
		Unmarshaller unmarshaller = contexto.createUnmarshaller();

		Calificaciones calificaciones = (Calificaciones) unmarshaller.unmarshal(new File(FICHERO_ENTRADA));

		for (TipoCalificacion calificacion : calificaciones.getCalificacion()) {
			double nuevaNota = Double.min(calificacion.getNota() + INCREMENTO, NOTA_MAXIMA);
			calificacion.setNota(nuevaNota);
		}

		for (TipoDiligencia diligencia : calificaciones.getDiligencia()) {
			double nuevaNota = Double.min(diligencia.getNota() + INCREMENTO, NOTA_MAXIMA);
			diligencia.setNota(nuevaNota);
		}

		Marshaller marshaller = contexto.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.setProperty("jaxb.schemaLocation", "http://www.example.org/calificaciones ejercicio3.xsd");

		marshaller.marshal(calificaciones, new File(FICHERO_SALIDA));

		System.out.println("Archivo '" + FICHERO_SALIDA + "', generado correctamente.");
		System.out.println("Fin.");

	}

}
