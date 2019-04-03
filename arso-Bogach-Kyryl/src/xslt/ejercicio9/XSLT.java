package xslt.ejercicio9;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLT {

	public static void main(String[] args) throws Exception {

		final String documentoEntrada = "xml/ejemplo-1-poema-dtd/poema.xml";
		final String documentoSalidaHTML = "xml/ejercicio9/poema-transformado.html";
		final String transformacionHTML = "xml/ejercicio9/plantilla-HTML.xsl";

		TransformerFactory factoria = TransformerFactory.newInstance();

		Transformer transformador = factoria.newTransformer(new StreamSource(transformacionHTML));
		Source origen = new StreamSource(documentoEntrada);
		Result destino = new StreamResult(documentoSalidaHTML);
		transformador.transform(origen, destino);

		System.out.println("HTML '" + documentoSalidaHTML + "' generado.");
	}
}
