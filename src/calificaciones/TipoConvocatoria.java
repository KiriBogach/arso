//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.03.12 a las 06:48:08 PM CET 
//


package calificaciones;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipo_convocatoria.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipo_convocatoria">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="febrero"/>
 *     &lt;enumeration value="junio"/>
 *     &lt;enumeration value="julio"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipo_convocatoria")
@XmlEnum
public enum TipoConvocatoria {

    @XmlEnumValue("febrero")
    FEBRERO("febrero"),
    @XmlEnumValue("junio")
    JUNIO("junio"),
    @XmlEnumValue("julio")
    JULIO("julio");
    private final String value;

    TipoConvocatoria(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoConvocatoria fromValue(String v) {
        for (TipoConvocatoria c: TipoConvocatoria.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
