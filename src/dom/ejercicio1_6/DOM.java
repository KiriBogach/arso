package dom.ejercicio1_6;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Bien.

public class DOM {

	public static void main(String[] args) throws Exception {

		final String FICHERO = "xml/ejercicio1.3/ejercicio3_diligencias.xml";

		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();

		// 3. Analizar el documento
		Document documento = analizador.parse(FICHERO);

		double sumaNota = 0;

		/* Hacemos dos búsquedas: primero cogemos todas las calificaciones
		 * y luego, miramos sus notas. 
		 * 
		 * Otra forma de resolver el problema podría ser coger todos los
		 * nodos 'nota' y mirar si su padre es un nodo con el nombre 
		 * de 'calificacion'. 
		 * 
		 * ¿Qué sería más eficiente?
		 */
		
		NodeList calificaciones = documento.getElementsByTagName("calificacion");
		for (int i = 0; i < calificaciones.getLength(); i++) {
			Element calificacion = (Element) calificaciones.item(i);
			Element nota = (Element) calificacion.getElementsByTagName("nota").item(0);
			sumaNota += Double.parseDouble(nota.getTextContent());
		}

		if (calificaciones.getLength() > 0) {
			System.out.println("Nota media: " + sumaNota / calificaciones.getLength());
		} else {
			System.out.println("El documento no tiene notas");
		}
		
		System.out.println("fin.");
	}
}
