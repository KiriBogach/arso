
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ParseIntResponse_QNAME = new QName("http://soap/", "parseIntResponse");
    private final static QName _ParseInt_QNAME = new QName("http://soap/", "parseInt");
    private final static QName _ConversorException_QNAME = new QName("http://soap/", "ConversorException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ParseInt }
     * 
     */
    public ParseInt createParseInt() {
        return new ParseInt();
    }

    /**
     * Create an instance of {@link ParseIntResponse }
     * 
     */
    public ParseIntResponse createParseIntResponse() {
        return new ParseIntResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseIntResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "parseIntResponse")
    public JAXBElement<ParseIntResponse> createParseIntResponse(ParseIntResponse value) {
        return new JAXBElement<ParseIntResponse>(_ParseIntResponse_QNAME, ParseIntResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseInt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "parseInt")
    public JAXBElement<ParseInt> createParseInt(ParseInt value) {
        return new JAXBElement<ParseInt>(_ParseInt_QNAME, ParseInt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "ConversorException")
    public JAXBElement<String> createConversorException(String value) {
        return new JAXBElement<String>(_ConversorException_QNAME, String.class, null, value);
    }

}
