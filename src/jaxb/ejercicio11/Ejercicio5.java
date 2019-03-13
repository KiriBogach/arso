package jaxb.ejercicio11;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import calificaciones.Calificaciones;
import calificaciones.TipoCalificacion;

public class Ejercicio5 {

	public static void main(String[] args) throws Exception {

		final String documento = "xml/ejercicio3/ejercicio3_diligencias.xml";

		JAXBContext contexto = JAXBContext.newInstance("calificaciones");
		Unmarshaller unmarshaller = contexto.createUnmarshaller();

		Calificaciones calificaciones = (Calificaciones) unmarshaller.unmarshal(new File(documento));

		double sumaNotas = 0;
		int numNotas = 0;

		for (TipoCalificacion calificacion : calificaciones.getCalificacion()) {
			sumaNotas += calificacion.getNota();
			numNotas++;
		}

		if (numNotas > 0) {
			System.out.println("Nota media: " + sumaNotas / numNotas);
		} else {
			System.out.println("No se han encontrado notas");
		}

		System.out.println("Fin.");

	}

}
