package sax.ejercicio1_5;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAX {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		
		// 1. Obtener una factoría
		SAXParserFactory factoria = SAXParserFactory.newInstance();
	
		// 2. Pedir a la factoría la construcción del analizador
		SAXParser analizador = factoria.newSAXParser(); 
		
		// 3. Analizar el documento

		try {
			Manejador manejador = new Manejador();
			analizador.parse("xml/ejercicio1.3/ejercicio3_diligencias.xml", manejador);
			
			System.out.println("Nota media: " + manejador.getNotaMedia());
		} 
		catch (IOException e) {
			System.out.println("El documento no ha podido ser leído");
		}
		catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
		}

		System.out.println("fin.");		
	}
}
