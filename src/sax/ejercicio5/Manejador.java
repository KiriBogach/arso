package sax.ejercicio5;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

	private LinkedList<String> pila;
	private boolean inCalificacion;
	private int numNotas;
	private double notaMedia;

	@Override
	public void startDocument() throws SAXException {
		this.pila = new LinkedList<>();
		this.inCalificacion = false;
		this.numNotas = 0;
		this.notaMedia = 0.0;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		pila.push(qName);

		if (qName.equalsIgnoreCase("calificacion")) {
			this.inCalificacion = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String texto = new String(ch, start, length).trim();
		String elemento = pila.peek();
		
		if (this.inCalificacion && elemento.equalsIgnoreCase("nota")) {
			
			try {
				double nota = Double.parseDouble(texto);
				this.addNotaMedia(nota);
			}
			catch (NumberFormatException e) {
				throw new SAXException("Error: Nota '" + texto + "' mal formado.");
			}
			
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		pila.pop();

		if (qName.equalsIgnoreCase("calificacion")) {
			this.inCalificacion = false;
		}
	}
	
	public double getNotaMedia() throws SAXException {
		if (this.numNotas == 0) {
			throw new SAXException("Error: No se han encontrado notas.");
		}
		
		return this.notaMedia / this.numNotas;
	}

	private void addNotaMedia(double value) {
		this.notaMedia += value;
		this.numNotas++;
	}
}
