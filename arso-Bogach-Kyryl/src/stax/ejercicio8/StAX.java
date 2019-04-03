package stax.ejercicio8;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

// Bien.

public class StAX {

	public static void main(String[] args) throws Exception {

		final String FICHERO_ENTRADA = "xml/ejercicio3/ejercicio3_diligencias.xml";
		XMLInputFactory xif = XMLInputFactory.newInstance();

		/* EJERCICIO 1.4 */
		XMLStreamReader reader = xif.createXMLStreamReader(new FileInputStream(FICHERO_ENTRADA));

		int numeroDiligencias = 0;

		Calendar calendario = Calendar.getInstance();
		SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaHoy = calendario.getTime();
		calendario.add(Calendar.DAY_OF_MONTH, -30);
		Date fechaHace30Dias = calendario.getTime();
		boolean inDiligencia = false;

		while (reader.hasNext()) {
			int evento = reader.next();

			if (evento == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("diligencia")) {
				inDiligencia = true;
			} else if (evento == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("diligencia")) {
				inDiligencia = false;
			} else if (inDiligencia && evento == XMLStreamConstants.START_ELEMENT
					&& reader.getLocalName().equals("fecha")) {

				String fecha = reader.getElementText();
				Date fechaDiligencia = simpleDataFormat.parse(fecha);

				// Manejamos el rango [fechaHace30días, hoy]
				if (fechaDiligencia.equals(fechaHoy) || fechaDiligencia.equals(fechaHace30Dias)
						|| (fechaDiligencia.after(fechaHace30Dias) && fechaDiligencia.before(fechaHoy))) {
					numeroDiligencias++;
				}
			}
		}

		reader.close();
		System.out.println("Número de diligencias encontradas: " + numeroDiligencias);

		/* EJERCICIO 1.5 */
		reader = xif.createXMLStreamReader(new FileInputStream(FICHERO_ENTRADA));
		double sumaNotas = 0;
		int numNotas = 0;

		boolean inCalificacion = false;

		while (reader.hasNext()) {
			int evento = reader.next();

			if (evento == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("calificacion")) {
				inCalificacion = true;
			} else if (evento == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("calificacion")) {
				inCalificacion = false;
			} else if (inCalificacion && evento == XMLStreamConstants.START_ELEMENT
					&& reader.getLocalName().equals("nota")) {

				double nota = Double.parseDouble(reader.getElementText());
				sumaNotas += nota;
				numNotas++;
			}
		}

		if (numNotas > 0) {
			System.out.println("Nota media: " + sumaNotas / numNotas);
		} else {
			System.out.println("No se han encontrado notas");
		}

		System.out.println("Fin.");
	}
}
