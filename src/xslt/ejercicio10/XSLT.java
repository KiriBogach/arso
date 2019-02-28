package xslt.ejercicio10;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLT {

	public static void main(String[] args) throws Exception {

		final String documentoEntrada = "xml/ejercicio9/poema-transformado.html";
		final String documentoSalidaXML = "xml/ejercicio10/poema-transformado.xml";
		final String transformacionXML = "xml/ejercicio10/plantilla-XML.xsl";
		

		TransformerFactory factoria = TransformerFactory.newInstance();

		Transformer transformador = factoria.newTransformer(new StreamSource(transformacionXML));
		Source origen = new StreamSource(documentoEntrada);
		Result destino = new StreamResult(documentoSalidaXML);
		transformador.transform(origen, destino);

		System.out.println("XML '" + documentoSalidaXML + "' generado.");
	}
}