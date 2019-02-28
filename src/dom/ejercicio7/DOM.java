package dom.ejercicio7;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Bien.

public class DOM {

	public static void main(String[] args) throws Exception {

		final String FICHERO_ENTRADA = "xml/ejercicio3/ejercicio3_diligencias.xml";
		final String FICHERO_SALIDA = "xml/ejercicio3/calificaciones-modificado.xml";
		final double NOTA_MAXIMA = 10.0;
		final double INCREMENTO = 0.5;

		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();

		// 3. Analizar el documento
		Document documento = analizador.parse(FICHERO_ENTRADA);
		
		NodeList calificaciones = documento.getElementsByTagName("nota");
		for (int i = 0; i < calificaciones.getLength(); i++) {
			Element nota = (Element) calificaciones.item(i);
			double notaActual = Double.parseDouble(nota.getTextContent());
			notaActual = Double.min(notaActual + INCREMENTO, NOTA_MAXIMA);
			nota.setTextContent(Double.toString(notaActual));
		}
		
		// 1. Construye la factoría de transformación y obtiene un
		// transformador
		TransformerFactory tFactoria = TransformerFactory.newInstance();
		Transformer transformacion = tFactoria.newTransformer();
		// 2. Establece la entrada, como un árbol DOM
		Source input = new DOMSource(documento);
		// 3. Establece la salida, un fichero en disco
		Result output = new StreamResult(FICHERO_SALIDA);
		// 4. Aplica la transformación
		transformacion.transform(input, output);
		
		System.out.println("fin.");
	}
}
