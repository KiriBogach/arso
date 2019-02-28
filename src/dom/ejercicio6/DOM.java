package dom.ejercicio6;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Bien.

public class DOM {

	public static void main(String[] args) throws Exception {

		final String FICHERO = "xml/ejercicio3/ejercicio3_diligencias.xml";

		// 1. Obtener una factor�a
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		// 2. Pedir a la factor�a la construcci�n del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();

		// 3. Analizar el documento
		Document documento = analizador.parse(FICHERO);

		double sumaNota = 0;

		/* Hacemos dos b�squedas: primero cogemos todas las calificaciones
		 * y luego, miramos sus notas. 
		 * 
		 * Otra forma de resolver el problema podr�a ser coger todos los
		 * nodos 'nota' y mirar si su padre es un nodo con el nombre 
		 * de 'calificacion'. 
		 * 
		 * �Qu� ser�a m�s eficiente?
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
