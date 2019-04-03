package sax.ejercicio4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

	private LinkedList<String> pila;
	private boolean inDiligencias;
	private int numeroDiligencias;
	private SimpleDateFormat simpleDataFormat;
	private Date fechaHoy;
	private Date fechaHace30Dias;

	@Override
	public void startDocument() throws SAXException {
		this.pila = new LinkedList<>();
		this.inDiligencias = false;
		this.numeroDiligencias = 0;
		this.simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendario = Calendar.getInstance();
		this.fechaHoy = calendario.getTime();
		calendario.add(Calendar.DAY_OF_MONTH, -30);
		this.fechaHace30Dias = calendario.getTime();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		pila.push(qName);

		if (qName.equalsIgnoreCase("diligencia")) {
			this.inDiligencias = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String texto = new String(ch, start, length).trim();
		String elemento = pila.peek();
		
		if (this.inDiligencias && elemento.equalsIgnoreCase("fecha")) {
			this.manageFechaDiligencia(texto);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		pila.pop();

		if (qName.equalsIgnoreCase("diligencia")) {
			this.inDiligencias = false;
		}
	}
	
	public int getNumeroDiligencias() {
		return numeroDiligencias;
	}

	private void incrementDiligencias() {
		this.numeroDiligencias++;
	}

	private void manageFechaDiligencia(String fecha) throws SAXException {
		Date fechaDiligencia = null;
		
		try {
			fechaDiligencia = this.simpleDataFormat.parse(fecha);
		} catch (ParseException e) {
			throw new SAXException("Error: Fecha '" + fecha + "' incorrecta.");
		}
		
		if (this.isIn30DaysRange(fechaDiligencia)) {
			this.incrementDiligencias();
		}
	}
	
	private boolean isIn30DaysRange(Date fecha) {
		if (fecha == null) {
			return false;
		}
		
		// Manejamos el rango [fechaHace30días, hoy]
		
		if (fecha.equals(fechaHoy) || fecha.equals(this.fechaHace30Dias)) {
			return true;
		}
		
		return (fecha.after(this.fechaHace30Dias) && fecha.before(fechaHoy));
	}
}
