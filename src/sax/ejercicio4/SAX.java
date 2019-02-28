package sax.ejercicio4;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/*
 * Revisi�n: bien.
 */


public class SAX {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		
		// 1. Obtener una factor�a
		SAXParserFactory factoria = SAXParserFactory.newInstance();
	
		// 2. Pedir a la factor�a la construcci�n del analizador
		SAXParser analizador = factoria.newSAXParser(); 
		
		// 3. Analizar el documento

		try {
			Manejador manejador = new Manejador();
			analizador.parse("xml/ejercicio3/ejercicio3_diligencias.xml", manejador);
			
			System.out.println("N�mero de diligencias encontradas: " + manejador.getNumeroDiligencias());
		} 
		catch (IOException e) {
			System.out.println("El documento no ha podido ser le�do");
		}
		catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
		}

		System.out.println("fin.");		
	}
}
