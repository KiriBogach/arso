package jaxb.ejercicio11;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import calificaciones.Calificaciones;
import calificaciones.TipoDiligencia;

public class Ejercicio4 {

	// Bien
	
	public static void main(String[] args) throws Exception {

		final String documento = "xml/ejercicio3/ejercicio3_diligencias.xml";

		JAXBContext contexto = JAXBContext.newInstance("calificaciones");
		Unmarshaller unmarshaller = contexto.createUnmarshaller();

		Calificaciones calificaciones = (Calificaciones) unmarshaller.unmarshal(new File(documento));

		Calendar calendario = Calendar.getInstance();
		Date fechaHoy = calendario.getTime();
		calendario.add(Calendar.DAY_OF_MONTH, -30);
		Date fechaHace30Dias = calendario.getTime();

		int numeroDiligencias = 0;

		for (TipoDiligencia diligencia : calificaciones.getDiligencia()) {
			Date fechaDiligencia = diligencia.getFecha().toGregorianCalendar().getTime();

			if (fechaDiligencia.equals(fechaHoy) || fechaDiligencia.equals(fechaHace30Dias)
					|| (fechaDiligencia.after(fechaHace30Dias) && fechaDiligencia.before(fechaHoy))) {
				numeroDiligencias++;
			}
		}

		System.out.println("Número de diligencias encontradas: " + numeroDiligencias);

		System.out.println("Fin.");

	}

}
